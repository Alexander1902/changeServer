package com.data.structure.tree;

/**
 * @description:
 * @author: zhangshancheng
 **/
public class NodeManager {
    private  Node root=null;

    /**
     * 增加节点
     * @param i
     * @return
     */
    public  boolean add(int i){
        boolean flge = false;
        if(root==null){
            root = new Node(i);
            flge = true;
        }else {
            Node parent=null;
            Node current =root;
            boolean isLeft = true;
            while (current!=null){
                if(i>current.node){
                    parent = current;
                    current = current.rigt;
                    isLeft = false;
                }else{
                    parent = current;
                    current = current.left;
                    isLeft =true;
                }
            }
            Node node = new Node(i);
            if(isLeft){
                parent.left=node;
            }else {
                parent.rigt = node;
            }
            flge = true;
        }
        return flge;
    }

    /**
     * 中序遍历
     */
    public void showMiddleOrder(){
        if(root!=null){
            middleOrder(root);
        }
    }

    private void  middleOrder(Node node){
        if(node.left!=null){
            middleOrder(node.left);
        }
        System.out.print(node.node+" ");
        if(node.rigt!=null){
            middleOrder(node.rigt);
        }
    }

    public void delete(int a){
         Node current =root;
         Node deleteNodeFather = null;
         Node temp = null;
        boolean isLeft = false;

         while(current!=null){
             temp = current;
             if(a>current.node){
                 current = current.rigt;
                 isLeft = false;
             }else if(a==current.node){
                 break;
             }else if(a<current.node){
                 current = current.left;
                 isLeft = true;
             }
             deleteNodeFather = temp;
         }
         if(current==null){
             System.out.println("没找到");
            return;
         }
         //处理后继节点
        Node deleteNode = isLeft ? deleteNodeFather.left : deleteNodeFather.rigt;
        Node successorFather = nextNode(deleteNode);
        //后继节点的父节点为空，则说明，删除节点没有子节点
         if(successorFather==null){
             if(isLeft){
                 deleteNodeFather.left=null;
             }else {
                 deleteNodeFather.rigt = null;
             }
             return;
         }else if(successorFather==deleteNode){
             //后继节点的父节点和删除节点相同，说明该删除节点的子节点只有一层，删除节点的直系子节点就是后继节点
             if(isLeft){
                 if(deleteNode.rigt!=null){
                     //将删除节点的右节点补位到删除节点位置
                     deleteNodeFather.left=deleteNode.rigt;
                     deleteNodeFather.left.left=deleteNode.left;
                 }else {
                     //直接将删除节点的左节点补位到删除节点位置
                     deleteNodeFather.left=deleteNode.left;
                 }
             }else {
                 if(deleteNode.rigt!=null){
                     deleteNodeFather.rigt=deleteNode.rigt;
                     deleteNodeFather.rigt.left=deleteNode.left;
                 }else{
                     deleteNodeFather.rigt=deleteNode.rigt;
                 }
             }
         }else{
             //拿到后继节点,连接后继节点的子节点与父节点
             Node successor = successorFather.left;
             if(successor.rigt!=null){
                 successorFather.left=successor.rigt;
             }else {
                 successorFather.left = null;
             }

             //连接删除节点的子节点与后继节点的子节点
             successor.rigt = deleteNode.rigt;
             successor.left = deleteNode.left;

             //连接后继节点与删除节点的父节点
             if(isLeft){
                 deleteNodeFather.left = successor;
             }else {
                 deleteNodeFather.rigt = successor;
             }
         }
    }

    /**'
     * 返回的是后继节点的父节点
     * @param node
     * @return
     */
    private Node nextNode(Node node){
        if(node.left==null&&node.rigt==null){
            return null;
        }else if(node.left!=null&&node.rigt==null){
            return node;
        }else if(node.left==null&&node.rigt!=null){
            return node;
        }else {
            Node fatherNode =node;
            Node current = node.rigt;
            while(current.rigt!=null){
                fatherNode = current;
                current = current.rigt;
            }
            return fatherNode;
        }
    }

}
