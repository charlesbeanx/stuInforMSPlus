package com.charles.utils;
import java.util.List;
/**
 * @author charlesBean
 * @create 2019-03-01 13:00
 * @email charlesbean@aliyun.com
 * @path MathInfoManaSystem/com.charles.utils/null.java
 */
public class PageHelper<T> {
    private Integer pageSize;
    private Integer pageIndex;
    private Integer totalCount;
    private Integer totalPage;
    private Integer startNum;
    private Integer endNum;
    private List<T> list;
    private T bean;
    public PageHelper() {
    }
    public PageHelper(Integer pageSize, Integer pageIndex, Integer totalCount, List<T> list, T bean) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalCount = totalCount;
        this.totalPage = totalCount%pageSize==0?(totalCount/pageSize):(totalCount/pageSize+1);
        this.list = list;
        this.bean = bean;
        if (totalPage<=10){
            this.startNum=1;
            this.endNum=totalPage;
        }else{
            this.startNum=pageIndex-4;
            this.endNum=pageIndex+5;
            if (this.startNum<=0){
                this.startNum=1;
                this.endNum=10;
            }else if(this.endNum>=totalPage){
                this.startNum=totalPage-9;
                this.endNum=totalPage;
            }
        }
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", startNum=" + startNum +
                ", endNum=" + endNum +
                ", list=" + list +
                ", bean=" + bean +
                '}';
    }
}
