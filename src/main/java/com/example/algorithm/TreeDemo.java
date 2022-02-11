package com.example.algorithm;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 1,A,0;2,B,1;3,c,2;4,D,2
 * @param
 */
public class TreeDemo {
    
    private static List<Department> departments;
    static Department root = null;

    private static void createDeppartment(String dataStr){

        String[] deptArr = dataStr.split(";");
        if(deptArr.length <3){
            System.out.println("error data");
            return;
        }
        departments = new ArrayList<>(deptArr.length);
        for(String deptStr:deptArr){

            String[] deptMateData = deptStr.split(",");
            if(deptMateData.length !=3){
                System.out.println("error deptMateData data");
                return;
            }
            Department department = new Department();
            department.setId(Integer.valueOf(deptMateData[0]));
            department.setName(deptMateData[1]);
            department.setPid(Integer.valueOf(deptMateData[2]));
            departments.add(department);
        }
    }

    /**
     * 创建树
     */
    public static void createTree(){
         if(departments == null || departments.size()==0){
             System.out.println("请先创建树");
             return;
         }

         for(Department department:departments){
               if(department.getId() == 0){
                   root = department;
               }
         }
        Iterator<Department> iterator = departments.iterator();
         while (iterator.hasNext()){
             if(iterator.next().getId() == 0){
                 iterator.remove();
             }
         }

        if(root == null){
             root = new Department();
             root.setPid(null);
             root.setName("root");
             root.setId(0);

         }
        recuCreate(root,departments);
    }

    public static void recuCreate(Department department,List<Department> list){

        Iterator<Department> iterator = list.iterator();
        while (iterator.hasNext()){
            Department current = iterator.next();
            if(current.getPid() == department.getId()){
                department.getNodes().add(current);
                iterator.remove();
                recuCreate(current,list);
            }
        }
    }
    
    static class Department{
        private Integer id;
        private String name;
        private Integer pid;
        private List<Department> nodes = new ArrayList<>();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public List<Department> getNodes() {
            return nodes;
        }

        public void setNodes(List<Department> nodes) {
            this.nodes = nodes;
        }
    }



    public static void main(String[] args) {
        createDeppartment("1,A,0;2,B,1;3,c,2;4,D,2");
        createTree();
        System.out.println(root.getNodes().size());

    }
}
