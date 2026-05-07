package com.ticketmaster.tmx.challenge.client;

import com.ticketmaster.tmx.challenge.model.HostTransferDto;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class HostTranserClientImpl implements HostTransferClient {
    private static final Map<String, List<HostTransferDto>> PENDING_TRANSFERS_BY_USER = Map.of(
            "user-123", List.of(
                    HostTransferDto.builder()
                            .transferId("transfer-1001")
                            .status("PENDING")
                            .senderEmail("sender1@example.com")
                            .recipientEmail("recipient1@example.com")
                            .eventId("evt-101")
                            .eventDate("2026-06-15")
                            .ticketIds(List.of("T-1", "T-2"))
                            .build(),
                    HostTransferDto.builder()
                            .transferId("transfer-1002")
                            .status("PENDING")
                            .senderEmail("sender2@example.com")
                            .recipientEmail("recipient2@example.com")
                            .eventId("evt-102")
                            .eventDate("2026-07-01")
                            .ticketIds(List.of("T-3"))
                            .build()),
            "user-empty", List.of()
    );

    @Override
    public Mono<List<HostTransferDto>> getPendingTransfers(String userId) {
        if (userId == null || userId.isBlank()) {
            return Mono.empty();
        }

        List<HostTransferDto> transfers = PENDING_TRANSFERS_BY_USER.get(userId);
        if (transfers == null || transfers.isEmpty()) {
            return Mono.empty();
        }

        return Mono.just(List.copyOf(transfers));
    }
}
