package com.erika.springshop.auth.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "customer-service", url = "${customer.service.url}")
public interface CustomerClient {
}
