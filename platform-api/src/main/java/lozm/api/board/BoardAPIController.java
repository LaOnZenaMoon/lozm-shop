package lozm.api.board;

import lombok.RequiredArgsConstructor;
import lozm.object.dto.ApiResponseCode;
import lozm.object.dto.ApiResponseDto;

import lozm.object.dto.board.*;
import lozm.object.vo.board.BoardVo;
import lozm.object.vo.board.CommentVo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController @RequestMapping(value = "/api/board")
@RequiredArgsConstructor
public class BoardAPIController {

    private final BoardService boardService;


    @GetMapping("/boardType/{boardType}")
    public ApiResponseDto getBoard(@PathVariable(value = "boardType") String boardType) {
        List<GetBoardDto> result = boardService.getBoardList(boardType);

        GetBoardDto.Response resDto = new GetBoardDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @GetMapping("/{boardId}")
    public ApiResponseDto getBoardDetail(@PathVariable(value = "boardId") Long boardId) {
        BoardVo boardVo = BoardVo.builder()
                .id(boardId)
                .build();

        GetBoardDto resDto = boardService.getBoardDetail(boardVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping
    public ApiResponseDto postBoard(@RequestBody @Valid PostBoardDto.Request reqDto) {
        BoardVo boardVo = BoardVo.builder()
                .boardType(reqDto.getBoardType())
                .contentType(reqDto.getContentType())
                .title(reqDto.getTitle())
                .content(reqDto.getContent())
                .createdBy(reqDto.getCreatedBy())
                .build();

        boardService.save(boardVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping
    public ApiResponseDto putBoard(@RequestBody @Valid PutBoardDto.Request reqDto) {
        BoardVo boardVo = BoardVo.builder()
                .id(reqDto.getId())
                .boardType(reqDto.getBoardType())
                .contentType(reqDto.getContentType())
                .title(reqDto.getTitle())
                .content(reqDto.getContent())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

        boardService.update(boardVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping
    public ApiResponseDto deleteBoard(@RequestBody @Valid DeleteBoardDto.Request reqDto) {
        for(DeleteBoardDto dto : reqDto.getList()) {
            BoardVo boardVo = BoardVo.builder()
                    .id(dto.getId())
                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            boardService.delete(boardVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @GetMapping("/{boardId}/comment")
    public ApiResponseDto getComment(@PathVariable(value = "boardId") Long boardId) {
        List<GetCommentDto> result = boardService.getCommentList(boardId);

        GetCommentDto.Response resDto = new GetCommentDto.Response();
        resDto.setList(result);

        return ApiResponseDto.createException(ApiResponseCode.OK, resDto);
    }

    @PostMapping("/comment")
    public ApiResponseDto postComment(@RequestBody @Valid PostCommentDto.Request reqDto) {
        CommentVo commentVo = CommentVo.builder()
                .boardId(reqDto.getBoardId())
                .commentType(reqDto.getCommentType())
                .content(reqDto.getContent())
                .createdBy(reqDto.getCreatedBy())
                .build();

        boardService.save(commentVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @PutMapping("/comment")
    public ApiResponseDto putComment(@RequestBody @Valid PutCommentDto.Request reqDto) {
        CommentVo commentVo = CommentVo.builder()
                .id(reqDto.getId())
                .commentType(reqDto.getCommentType())
                .content(reqDto.getContent())
                .modifiedBy(reqDto.getModifiedBy())
                .build();

        boardService.update(commentVo);

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

    @DeleteMapping("/comment")
    public ApiResponseDto deleteComment(@RequestBody @Valid DeleteCommentDto.Request reqDto) {
        for(DeleteCommentDto dto : reqDto.getList()) {
            CommentVo commentVo = CommentVo.builder()
                    .id(dto.getId())

                    .modifiedBy(reqDto.getModifiedBy())
                    .build();

            boardService.delete(commentVo);
        }

        return ApiResponseDto.createException(ApiResponseCode.OK, null);
    }

}
