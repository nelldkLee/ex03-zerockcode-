package org.zerock.persistence;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;

import java.util.List;

/**
 * @author LeeSoohoon
 */
public interface BoardDAO {

    void create(BoardVO vo) throws Exception;

    BoardVO read(Integer bno) throws Exception;

    void update(BoardVO vo) throws Exception;

    void delete(Integer bno) throws Exception;

    List<BoardVO> listAll() throws Exception;

    List<BoardVO> listPage(int page) throws Exception;

    List<BoardVO> listCriteria(Criteria cri) throws Exception;

    int countPaging(Criteria cri) throws Exception;

    List<BoardVO> listSearch(SearchCriteria cri) throws Exception;

    int listSearchCount(SearchCriteria cri) throws Exception;

    void updateReplyCnt(Integer bno, int amount) throws Exception;

    void updateViewCnt(Integer bno) throws Exception;

    void addAttach(String fullName) throws Exception;

    List<String> getAttach(Integer bno) throws Exception;

    void deleteAttach(Integer bno) throws Exception;

    void replaceAttach(String fullName, Integer bno) throws Exception;
}
