package lozm.object.dto.coupon;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DeleteCouponUserDto {

    @Getter
    public static class Request extends BaseUserDto {
        @Size(min = 1)
        private List<GetCouponUserDto> list = new ArrayList<>();
    }

}
