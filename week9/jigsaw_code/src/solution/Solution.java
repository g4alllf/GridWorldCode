package solution;

import java.util.LinkedList;
import java.util.Vector;
import java.util.Queue;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
    double T = 30;
    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
        Queue<JigsawNode> nodes = new LinkedList<JigsawNode>();
        Vector<JigsawNode> isVisited = new Vector<JigsawNode>();
        nodes.add(bNode);
        isVisited.add(bNode);
        while(!nodes.isEmpty()) {
            if(nodes.peek().equals(eNode)) {
                currentJNode = nodes.peek();
                getPath();
                System.out.println(getSearchedNodesNum());
                System.out.println(getSolutionPath());
                return true;
            }
            for(int dir = 0; dir < 4; dir++) {
                JigsawNode temp = new JigsawNode(nodes.peek());
                if(temp.move(dir)) {
                    if(!isVisited.contains(temp)) {
                        nodes.add(temp);
                        isVisited.add(temp);
                    }
                }
            }
            nodes.poll();
        }
        return false;
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        jNode.setEstimatedValue(s + cost(jNode));
    }

    /**
     * Calculate the cost to change the JNode to its final state.
     * @param node is the target node.
     * @return the cost to change the state.
     */
    private int cost(JigsawNode node) {
        int cost = 0;
        int position;
        int dimension = JigsawNode.getDimension();
        int[] state = node.getNodesState();
        int[] endState = endJNode.getNodesState();
        for(int i = 1; i < dimension*dimension + 1; i++) {
            if(state[i] == 0) {
                continue;
            }
            for(int j = 1; j < state.length; j++) {
                if(state[i] == endState[j]) {
                    position = j;
                } else {
                    position = -1;
                }

                int x1 = (i-1) / 5;
                int x2 = (position-1) / 5;
                int y1 = (i-1) % 5;
                int y2 = (position-1) % 5;

                cost += Math.floor(Math.abs(x2 - x1) + Math.abs(y2 - y1));
            }
        }
        return cost;
    }
}
