package com.ticketmaster.tmx.challenge.service;

import com.ticketmaster.tmx.challenge.client.HostTransferClient;
import com.ticketmaster.tmx.challenge.converter.TransferInviteConverter;
import com.ticketmaster.tmx.challenge.model.HostTransferDto;
import com.ticketmaster.tmx.challenge.model.TransferInviteModel;
import com.ticketmaster.tmx.challenge.model.TransferStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferInviteServiceTest {

    private HostTransferClient hostTransferClient;
    private TransferInviteService service;

    @BeforeEach
    void setUp() {
        hostTransferClient = mock(HostTransferClient.class);
        TransferInviteConverter converter = new TransferInviteConverter();
        service = new TransferInviteService(hostTransferClient, converter);
    }

    @Test
    void getActiveInvitesSync() {
        String userId = "user-123";

        List<HostTransferDto> hostTransfers = List.of(
                HostTransferDto.builder()
                        .transferId("transfer-1")
                        .status("PENDING")
                        .senderEmail("sender@example.com")
                        .recipientEmail("recipient@example.com")
                        .eventId("event-1")
                        .eventDate("2026-09-15")
                        .ticketIds(List.of("t1", "t2"))
                        .build()
        );

        when(hostTransferClient.getPendingTransfers(userId)).thenReturn(Mono.just(hostTransfers));

        List<TransferInviteModel> result = service.getActiveInvites(userId).block();

        TransferInviteModel expected = TransferInviteModel.builder()
                .id("transfer-1")
                .status(TransferStatus.PENDING)
                .senderEmail("sender@example.com")
                .recipientEmail("recipient@example.com")
                .eventId("event-1")
                .eventDate(LocalDate.of(2026, 9, 15))
                .ticketCount(2)
                .build();

        assertEquals(List.of(expected), result);
        verify(hostTransferClient).getPendingTransfers(userId);
    }

    @Test
    void getActiveInvites() {
        String userId = "user-456";

        List<HostTransferDto> hostTransfers = List.of(
                HostTransferDto.builder()
                        .transferId("transfer-2")
                        .status("PENDING")
                        .senderEmail("another-sender@example.com")
                        .recipientEmail("another-recipient@example.com")
                        .eventId("event-2")
                        .eventDate("2026-10-10")
                        .ticketIds(List.of("t3"))
                        .build()
        );

        when(hostTransferClient.getPendingTransfers(userId)).thenReturn(Mono.just(hostTransfers));

        TransferInviteModel expected = TransferInviteModel.builder()
                .id("transfer-2")
                .status(TransferStatus.PENDING)
                .senderEmail("another-sender@example.com")
                .recipientEmail("another-recipient@example.com")
                .eventId("event-2")
                .eventDate(LocalDate.of(2026, 10, 10))
                .ticketCount(1)
                .build();

        StepVerifier.create(service.getActiveInvites(userId))
                .assertNext(result -> assertEquals(List.of(expected), result))
                .verifyComplete();

        verify(hostTransferClient).getPendingTransfers(userId);
    }
}
