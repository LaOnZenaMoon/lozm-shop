package lozm.api.user;

import lombok.RequiredArgsConstructor;
import lozm.core.dto.APIResponseDto;
import lozm.core.dto.user.GetUserDto;
import lozm.core.dto.user.PostUserCouponDto;
import lozm.core.dto.user.PostUserDto;
import lozm.core.dto.user.PutUserDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/user")
@RestController
@RequiredArgsConstructor
public class UserAPIController {

    private final UserService userService;


    @GetMapping()
    public APIResponseDto getUser() {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            List<GetUserDto.Response> result = userService.findAllUsers();
            resDto.setSuccess(true);
            resDto.setData(result);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping
    public APIResponseDto postUser(@RequestBody @Valid PostUserDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            userService.save(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PutMapping
    public APIResponseDto putUser(@RequestBody @Valid PutUserDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            userService.update(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

    @PostMapping(value = "/coupon")
    public APIResponseDto postUserCoupon(@RequestBody @Valid PostUserCouponDto.Request reqDto) {
        APIResponseDto resDto = new APIResponseDto<>();

        try {
            userService.saveUserCoupon(reqDto);
            resDto.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resDto.setSuccess(false);
            resDto.setMessage(e.getMessage());
        }

        return resDto;
    }

}
