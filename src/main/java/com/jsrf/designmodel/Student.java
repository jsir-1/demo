package com.jsrf.designmodel;

import com.google.common.base.MoreObjects;

/**
 * 将一个复杂的对象的构建和表示进行分离，使得同样的构建过程可以创建不同的标示形式。构建者模式隐藏了复杂对象的创建过程
 *
 * @author jsrf
 */
public class Student {
    private int id;
    private String name;
    private String password;
    private String sex;
    private String address;

    private Student() {
    }

    /**
     * Student的创建完全依靠Student.Builder，使用一种方法链的方式来创建
     *
     * @param origin
     */
    private Student(Student origin) {
        // 拷贝一份
        this.id = origin.id;
        this.name = origin.name;
        this.password = origin.password;
        this.sex = origin.sex;
        this.address = origin.address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public static class Builder {

        private Student target;

        public Builder() {
            target = new Student();
        }

        public Builder id(int id) {
            target.id = id;
            return this;
        }

        public Builder name(String name) {
            target.name = name;
            return this;
        }

        public Builder password(String password) {
            target.password = password;
            return this;
        }

        public Builder sex(String sex) {
            target.sex = sex;
            return this;
        }

        public Builder address(String address) {
            target.address = address;
            return this;
        }

        public Student build() {
            return new Student(target);
        }

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("password", password)
                .add("sex", sex)
                .add("address", address)
                .toString();
    }

    public static void main(String[] args) {
        Student build = new Builder().address("beijing").name("jsir").password("1244e").sex("man").build();
        System.out.println(build);

    }

}
