package lozm.repository.board;

import lozm.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT B FROM Board B WHERE B.boardType = :boardType ORDER BY B.createdDt DESC")
    List<Board> selectBoardListByBoardType(String boardType);

}
