package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

import java.util.List;

import javax.inject.Inject;

/**
 * @author LeeSoohoon
 */
@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO dao;

    @Transactional
    @Override
    public void regist(final BoardVO board) throws Exception {
        dao.create(board);

        final String[] files = board.getFiles();

        if (files == null) {
            return;
        }

        for (String fileName : files) {
            dao.addAttach(fileName);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO read(final Integer bno) throws Exception {
        dao.updateViewCnt(bno);
        return dao.read(bno);
    }

    @Transactional
    @Override
    public void modify(final BoardVO board) throws Exception {
        dao.update(board);

        final Integer bno = board.getBno();

        dao.deleteAttach(bno);

        final String[] files = board.getFiles();

        if (files == null) {
            return;
        }

        for (String fileName : files) {
            dao.replaceAttach(fileName, bno);
        }

    }

    @Transactional
    @Override
    public void remove(final Integer bno) throws Exception {
        dao.deleteAttach(bno);
        dao.delete(bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return dao.listAll();
    }

    @Override
    public List<BoardVO> listCriteria(final Criteria cri) throws Exception {
        return dao.listCriteria(cri);
    }

    @Override
    public int listCountCriteria(final Criteria cri) throws Exception {
        return dao.countPaging(cri);
    }

    @Override
    public List<BoardVO> listSearchCriteria(final SearchCriteria cri) throws Exception {
        return dao.listSearch(cri);
    }

    @Override
    public int listSearchCount(final SearchCriteria cri) throws Exception {
        return dao.listSearchCount(cri);
    }

    @Override
    public List<String> getAttach(final Integer bno) throws Exception {
        return dao.getAttach(bno);
    }
}
