package com.jett.algorithm.treecount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 累加树
 * 从叶子节点开始，本身值=[各子结点的值+自身值]
 */
public class TreeCount {

    public static void main(String[] args) {
        TreeCount tc = new TreeCount();
        List<Row> rows = tc.initList();

        Map<String, List<Row>> treeMap = new HashMap(); // 单层父子结构，父id:子信息组
        Map<String, Row> rowMap = new HashMap(); // 将List转成Map，id:row，方便快速通过map.get(id)方法查找row信息
        for (Row row : rows) {
            rowMap.put(row.getId(), row); // id->pid，单向map
            List subs = treeMap.get(row.getPid());
            if (subs == null) {
                List subTemp = new ArrayList<Row>();
                subTemp.add(row);
                treeMap.put(row.getPid(), subTemp);
            } else {
                subs.add(row);
            }
        }
        for (Map.Entry<String, List<Row>> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        System.out.println("================");
        System.out.println(rowMap);
        String root = tc.findRoot(rowMap, rowMap.values().iterator().next());
        System.out.println(root);
        tc.calc(treeMap, rowMap, root);
        System.out.println("======= calc ======");
        System.out.println(rowMap);
    }

    private String findRoot(Map<String, Row> levelMap, Row row) {
        Row pRow = levelMap.get(row.getPid());
        if (pRow != null) { // 还有父结点
            return findRoot(levelMap, pRow);
        }
        return row.getPid();
    }

    private int calc(Map<String, List<Row>> treeMap, Map<String, Row> rowMap, String id) {
        List<Row> sonRows = treeMap.get(id);
        Row row = rowMap.get(id);
        if (sonRows == null) { // 叶子结点处理
            row.setTotal(row.getNum());
            return row.getTotal();
        }
        int subTotal = 0;
        for (Row r : sonRows) {
            subTotal += calc(treeMap, rowMap, r.getId());
        }
        if (row == null) { // 根结点处理
            row = new Row(id, "", 0);
            rowMap.put(id,row);
        }
        row.setTotal(row.getNum()+subTotal);
        return row.getTotal();
    }

    public List<Row> initList() {
        ArrayList<Row> rows = new ArrayList<>();
        rows.add(new Row("a2", "a", 4));
        rows.add(new Row("a1", "a", 2));
        rows.add(new Row("a", "-1", 1));
        rows.add(new Row("a21", "a2", 40));
        rows.add(new Row("c", "-1", 32));
        rows.add(new Row("b1", "b", 16));
        rows.add(new Row("b", "-1", 8));

        return rows;
    }

    class Row {
        String id;
        String pid;
        int num;
        int total;  // = num + sum(son)

        public Row(String id, String pid, int num){
            this.id = id;
            this.pid = pid;
            this.num = num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "id='" + id + '\'' +
                    ", pid='" + pid + '\'' +
                    ", num=" + num +
                    ", total=" + total +
                    '}';
        }
    }
}
