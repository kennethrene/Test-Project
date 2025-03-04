package gcp.dto;

public record CommissionStatement(String id, String reportYearMonth, String agencyTrackingCode, Long creationDate, String creationDateText) {
}
