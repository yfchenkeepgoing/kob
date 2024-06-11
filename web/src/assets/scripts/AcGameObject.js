// 所有游戏对象的基类
// 为了将所有游戏对象都刷新一遍，需要将所有游戏对象存储下来
const AC_GAME_OBJECTS = []; // 全局变量存储游戏对象

// 需要export出去，方便引用
export class AcGameObject {
    // 构造函数
    constructor() {
        AC_GAME_OBJECTS.push(this); // 存储一个游戏对象
        this.timedelta = 0; // 速度涉及到两帧之间的时间间隔
        this.has_called_start = false; // 记录是否执行过，没有执行过时为false
    }

    // 游戏对象的函数
    start() { // 创建游戏对象时执行一次

    }

    update() { // 每一帧执行一次，除了第一帧之外

    }

    on_destroy() { // 删除游戏对象之前执行的函数

    }

    destroy() { // 删除游戏对象
        this.on_destroy(); // 删除游戏对象前调用on_destroy函数
        // 将当前游戏对象从AC_GAME_OBJECTS中删除即可
        for (let i in AC_GAME_OBJECTS) {
            const obj = AC_GAME_OBJECTS[i];
            if (obj == this) {
                AC_GAME_OBJECTS.splice(i); // 删除obj
                break;
            }
        }
    }
}

let last_timestamp; // 上一次执行的时刻

// 实现每秒所有游戏对象都被刷新60次
// 每帧都执行step函数
// timestamp为当前函数执行的时刻
const step = timestamp => {
    for (let obj of AC_GAME_OBJECTS) {// js中of遍历的是值，in遍历的是下标
        // 未执行start函数，则执行start函数
        if (!obj.has_called_start) {
            obj.has_called_start = true;
            obj.start();
        } else { // 执行update函数
            obj.timedelta = timestamp - last_timestamp; // 计算时间间隔
            obj.update();
        }
    }

    last_timestamp = timestamp;
    // 需要step函数每帧都执行一次，将其写成递归函数即可
    requestAnimationFrame(step)
}

requestAnimationFrame(step) // step函数会在浏览器渲染下一帧前执行一遍

