package com.jsrf.excel;

import lombok.Data;

import java.util.List;

/**
 * @author calmer
 * @since 2018/12/5 14:50
 */
@Data
public class Person extends Parent {
    private Integer id;
    private String name;
    private Integer age;
    private String hobby;
    private String job;
    private String address;
    private Boolean man;
    private boolean ismanw;
    private boolean manw;
    private String father;
    private List<String> list;
    private Boolean iswoismen;

}
