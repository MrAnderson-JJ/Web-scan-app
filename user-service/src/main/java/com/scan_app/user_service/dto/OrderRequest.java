package com.web_scan.user.dto;

import java.math.BigDecimal;

public record OrderRequest(Long id, String name, String description, BigDecimal price,String skuCode , Integer quantity) {
}
