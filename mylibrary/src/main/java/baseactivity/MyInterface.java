package baseactivity;

/**
 * Created by Liuxiaoyu on 2016/11/8.
 */
public  interface MyInterface {
    /**
     * 返回布局ID
     * @return
     */
    int getLayout();

    /**
     * 初始化控件
     */
    void initUI();
    /**
     * 初始化数据
     */
    void getData();
    /**
     * 请求网络资源
     */
    void loadData();

}
