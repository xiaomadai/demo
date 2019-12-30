package com.xmd.firstBoot.entity;

import org.springframework.util.StringUtils;

/**
 * @Author: GCG
 * @Description:
 * @Date: Created in 17:46 2019/12/5
 */
public class User {

    private String id;
    private String userName;
    private String passWord;

    public User(String id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;//地址相等
        }

        if(obj == null){
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if(obj instanceof User){
            User other = (User) obj;
            //需要比较的字段相等，则这两个对象相等
            if(equalsStr(this.userName, other.userName)
                    /*&& equalsStr(this.passWord, other.passWord)*/){
                return true;
            }
        }

        return false;
    }

    private boolean equalsStr(String str1, String str2){
        if(StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)){
            return true;
        }
        if(!StringUtils.isEmpty(str1) && str1.equals(str2)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (userName == null ? 0 : userName.hashCode());
        //result = 31 * result + (passWord == null ? 0 : passWord.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public static void main(String[] args){
        User user1 = new User("1","张三","admin");
        User user2 = new User("2","张三","admin");
        User user3 = new User("3","张三","admin");
        System.out.println(user1.equals(user1));
        System.out.println(user1.equals(user2));
        System.out.println(user2.equals(user1));

        System.out.println(user1.equals(user2)+""+user2.equals(user3)+""+user1.equals(user3));
    }
}
