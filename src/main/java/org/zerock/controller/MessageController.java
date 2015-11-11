package org.zerock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.MessageVO;
import org.zerock.service.MessageService;

import javax.inject.Inject;

/**
 * @author LeeSoohoon
 */
@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Inject
    private MessageService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> addMessage(@RequestBody MessageVO vo) {

        ResponseEntity<String> entity;

        try {
            service.addMessage(vo);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
