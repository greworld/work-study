package com.gupaoedu.nazgd.fifo;

import org.I0Itec.zkclient.ExceptionUtil;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 模拟FIFO（先进先出队列）
 * Created by Zhanggd on 2017/8/12.
 */
public class DistributeQueue <T>{

    private String root;//根节点

    private static String node_Name = "x_";//顺序节点的名称

    private ZkClient zkClient;//ZooKeeper客户端对象

    private IZkDataListener dataListener;//监听节点删除事件，通知生产者和所有消费者，数据已经被消费了

    public DistributeQueue(ZkClient zkClient,String root) {
        this.zkClient =zkClient;
        this.root = root;
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    /**
     * 生产队列,同时存入节点数据信息
     * @param element
     * @return
     * @throws Exception
     */
    public boolean offer(T element) throws Exception{
        //构建数据节点的完整路径
        String nodeFullPath = root.concat("/").concat(node_Name);
        System.out.println("开始装载节点");
        try {
            //创建持久的节点，写入数据
            zkClient.createPersistentSequential(nodeFullPath , element);
        }catch (ZkNoNodeException e) {
            boolean exists = zkClient.exists(root);
            //当exists为false时，说明没有根据节点需要创建
            if(!exists){
                zkClient.createPersistent(root);
                offer(element);
            }else {
                System.out.println("创建错误");
            }
        }
        catch (Exception e) {
            throw ExceptionUtil.convertToRuntimeException(e);
        }
        return true;
    }

    /**
     * 消费节点数据
     */
    public T poll(){
        List<String> children = zkClient.getChildren(root);
        if (children.size()==0){
            System.out.println("队列中没有数据...");
            return null;
        }
        //获取队列中所有的任务

        //排序:使用Collections.sort()
        Collections.sort(children, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return getNodeNumber(lhs,node_Name).compareTo(getNodeNumber(rhs,node_Name));
            }
        });

        for (String nodeName:children){
            //节点的完整路径
            String nodePath = root.concat("/").concat(nodeName);
            try {
                //读取到数据
                T node = zkClient.readData(nodePath);
                //消费数据
                zkClient.delete(nodePath);
                return node;
            } catch (ZkNoNodeException e) {
                throw ExceptionUtil.convertToRuntimeException(e);
            }

        }
        return null;
    }

    /**
     * 判断队列是否空
     */
    public boolean isEmpty(){
        if(zkClient.getChildren(root).size()==0){
            return true;
        }
        return true;
    }

    /**
     * 获取队列数据的大小
     */
    public int size(){
        return zkClient.getChildren(root).size();
    }

    /**
     * 指定比较方法，
     * @param str
     * @param nodeName
     * @return
     */
    public static String getNodeNumber(String str, String nodeName){
        int index = str.lastIndexOf(nodeName);
        if (index>0){
            index +=nodeName.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }

    private void sendMsg(String node){
        System.out.println("节点["+node+"]已经被消费");
    }

}
