// AcGameObject被export了，因此需要在引用时用{}
// 若export default，则引用时不需要{}，export default类似于java中的public
import { AcGameObject } from "./AcGameObject";

export class GameMap extends AcGameObject {
    // 构造函数
    // 两个参数: ctx: 画布; parent: 画布的父元素, 用于动态修改画布的长宽
    constructor(ctx, parent) {
        super(); // 执行基类的构造函数

        this.ctx = ctx; // 存下ctx
        this.parent = parent; 

        // 缩放浏览器，地图大小会改变，所有游戏对象也应该成比例改变
        // 不用绝对距离而用相对距离
        this.L = 0; // 地图大小为13*13，存下一个单位的绝对距离，以后坐标都用相对距离表示

        // 行数和列数
        this.rows = 13;
        this.cols = 13;
    }

    // 第一帧执行start函数
    start() {

    }

    // 辅助函数
    update_size() {
        // 矩形的游戏区域中能放下的最大正方形的单位边长，该正方形作为游戏地图
        this.L = Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows); 
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    // 第二帧开始每一帧执行update函数
    update() {
        this.update_size(); // 辅助函数，用于在每一帧更新边长
        this.render();
    }

    // 用于在update函数中渲染每一帧，即将当前的游戏对象画到画布上
    render() {
        this.ctx.fillStyle = 'green';
        // 前两个数为左上角坐标，后两个数表示边长
        this.ctx.fillRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
    }
}

