package com.xh;

import lombok.Data;

/**
 * @author Xiao Hong
 * @since 2023-03-02
 */
@Data
public class Status {

    private Long id;

    private Long value;

    public Status() {
    }

    public Status(Long id, Long value) {
        this.id = id;
        this.value = value;
    }
}
