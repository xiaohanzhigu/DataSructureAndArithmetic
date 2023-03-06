package com.ch.arithmetic.huffman;


import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        //String content = "i like like like java do you like a java";
        //byte[] contentBytes = content.getBytes();
        //List<Node> nodes = toNodeList(contentBytes);
        //System.out.println("结点: " + nodes);
        //Node huffmanRoot = createHuffman(nodes);
        //Map<Byte, String> coding = createCoding(huffmanRoot);
        //System.out.println("生成的编码表: " + coding);
        //byte[] zip = zip(contentBytes, coding);
        //System.out.println("压缩后的字节数组: " + Arrays.toString(zip));

        //byte[] bytes = huffmanZip(content.getBytes());
        //byte[] sourceBytes = decode(bytes, huffmanCodes);
        //System.out.println("字符: " + new String(sourceBytes));
        String srcFile = "D:\\Java\\数据结构\\资料\\压缩测试文件\\src.bmp";
        String descFile = "D:\\Java\\数据结构\\资料\\压缩测试文件\\src.zip";
        zipFile(srcFile, descFile);
        srcFile = "D:\\Java\\数据结构\\资料\\压缩测试文件\\src.zip";
        descFile = "D:\\Java\\数据结构\\资料\\压缩测试文件\\src2.bmp";
        unZipFile(srcFile, descFile);

    }

    public static void unZipFile(String srcFile, String descFile) {
        try (FileInputStream is = new FileInputStream(srcFile);
             ObjectInputStream ois = new ObjectInputStream(is);
             FileOutputStream os = new FileOutputStream(descFile)) {
            byte[] bytes = new byte[is.available()];
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(huffmanBytes, huffmanCodes);
            os.write(decode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFile(String srcFile, String descFile) {
        try (FileInputStream is = new FileInputStream(srcFile);
             FileOutputStream os = new FileOutputStream(descFile);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            byte[] huffmanZip = huffmanZip(bytes);
            oos.writeObject(huffmanZip);
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解压赫夫曼压缩后在字节
     *
     * @param bytes        赫夫曼编码得到的字节数组
     * @param huffmanCodes 赫夫曼编码表 map
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder builder = new StringBuilder();
        //1. 先得到 huffmanBytes 对应的 二进制的字符串
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            boolean flag = (i == bytes.length - 1);
            String s = byteToString(!flag, b);
            builder.append(s);
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < builder.length(); ) {
            boolean flag = true;
            int count = 1;
            Byte b = null;
            while (flag) {
                String s = builder.substring(i, i + count);
                b = map.get(s);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    private static String byteToString(boolean flag, byte b) {
        int temp = b;
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256;
        }
        String s = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }

    }

    /**
     * 赫夫曼数据压缩
     *
     * @param bytes 要压缩的内容
     * @return 内容压缩后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodeList = toNodeList(bytes);
        Node huffmanRoot = createHuffman(nodeList);
        Map<Byte, String> coding = createCoding(huffmanRoot);
        return zip(bytes, coding);
    }

    /**
     * 将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     *
     * @param bytes         原始的字符串对应的 byte[]
     * @param huffmanCoding 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCoding) {
        StringBuilder builder = new StringBuilder();
        //利用 huffmanCoding 将  bytes 转成赫夫曼编码对应的字符串
        for (Byte aByte : bytes) {
            builder.append(huffmanCoding.get(aByte));
        }

        //将字符串每8位构成一个byte
        int len = (builder.length() + 7) / 8;
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录HuffmanCodeBytes的索引位置
        for (int i = 0; i < builder.length(); i += 8) {
            String strByte;
            if (i + 8 < builder.length()) {
                strByte = builder.substring(i, i + 8);
            } else {
                strByte = builder.substring(i);
            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    //生成的赫夫曼编码表
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 生成赫夫曼树对应的编码表
     *
     * @param root 赫夫曼树的根节点
     * @return 生成的编码表
     */
    private static Map<Byte, String> createCoding(Node root) {
        if (root == null)
            return null;
        createCoding(root.left, "0", stringBuilder);
        createCoding(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 生成赫夫曼树对应的编码表
     * 将赫夫曼编码表存放在 Map<Byte,String> 形式
     *
     * @param node          传入结点
     * @param code          路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 拼接路径
     */
    private static void createCoding(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder(stringBuilder);
        builder.append(code);
        if (node != null) {
            if (node.data == null) { //等于null说明不是非叶子结点
                createCoding(node.left, "0", builder);
                createCoding(node.right, "1", builder);
            } else {
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }

    /**
     * 根据nodeList生成赫夫曼树
     *
     * @param list 要生成树的叶子节点
     * @return 树的根节点
     */
    private static Node createHuffman(List<Node> list) {
        while (list.size() > 1) {
            Collections.sort(list);

            Node left = list.get(0);
            Node right = list.get(1);

            Node parent = new Node();
            parent.left = left;
            parent.right = right;
            parent.weight = left.weight + right.weight;

            list.remove(1);
            list.remove(0);
            list.add(parent);
        }
        return list.get(0);
    }

    private static List<Node> toNodeList(byte[] bytes) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : bytes) {
            int count = 1;
            if (map.containsKey(aByte)) { //如果map中有这个key，把他出现的次数 + 1
                count = map.get(aByte) + 1;
            }
            map.put(aByte, count);
        }

        List<Node> list = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    private static void preOrder(Node root) {
        System.out.print("preOrder: ");
        if (root != null) {
            root.preOrder();
        }
        System.out.println();
    }

}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public Node() {
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.print(this + ",");
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (weight != node.weight) return false;
        return Objects.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + weight;
        return result;
    }
}
