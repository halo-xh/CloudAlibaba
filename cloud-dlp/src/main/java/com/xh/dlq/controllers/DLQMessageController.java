package com.xh.dlq.controllers;

import com.xh.dlq.dto.DLQMessageSummary;
import com.xh.dlq.services.DLQMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dlq")
public class DLQMessageController {

    @Autowired
    private DLQMessageService dlqMessageService;

    @GetMapping(value = "")
    public Page<DLQMessageSummary> page(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return dlqMessageService.page(pageable).map(DLQMessageSummary::from);
    }

    @PostMapping(value = "/redo/{dlqMsgId}")
    public void redo(@PathVariable Long dlqMsgId) {
        dlqMessageService.redo(dlqMsgId);
    }

    @DeleteMapping(value = "/markAsDeleted/{dlqMsgId}")
    public void markAsDeleted(@PathVariable Long dlqMsgId) {
        dlqMessageService.markAsDeleted(dlqMsgId);
    }
}
