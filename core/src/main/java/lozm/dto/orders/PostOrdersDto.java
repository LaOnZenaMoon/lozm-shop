package lozm.dto.orders;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class PostOrdersDto {

    @Getter
    public static class Request {
        @NotNull
        private Long userId;

        @NotNull
        private Long itemId;

        private Long couponId;

        @NotNull
        private Long deliveryId;

        @NotNull
        private Long quantity;

        public static Request setRequestTestData(Long userId, Long itemId, Long deliveryId, Long couponId, Long quantity) {
            PostOrdersDto.Request reqDto = new PostOrdersDto.Request();
            reqDto.userId = userId;
            reqDto.itemId = itemId;
            reqDto.deliveryId = deliveryId;
            reqDto.couponId = couponId;
            reqDto.quantity = quantity;

            return reqDto;
        }
    }



}
