package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 글 작성
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID(); //랜덤으로 이름을 만들어줌

        String fileName = uuid + "_" + file.getOriginalFilename(); //랜덤이름_파일이름

        File saveFile = new File(projectPath, fileName); // 파일생성(경로, 이름)

        file.transferTo(saveFile);
        
        board.setFilename(fileName); //파일이름
        board.setFilepath("/files/" + fileName); //저장된 파일 경로와 아름

        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id){

        return boardRepository.findById(id).get();
    }

    public Page<Board> bordSearchList(String searchKeyword, Pageable pageable){

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 삭제

    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }
}
