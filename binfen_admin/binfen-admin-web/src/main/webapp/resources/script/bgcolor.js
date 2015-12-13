// JavaScript Document
function onloadEvent(func) {
    var one = window.onload
    if (typeof window.onload != 'function') {
        window.onload = func
    }
    else {
        window.onload = function() {
            one();
            func();
        }
    }
}
function showtable() {
    var tableid = 'table';//表格的id
    var overcolor = '#FCF9D8';//鼠标经过颜色
    var color1 = '#FFFFFF';//第一种颜色
    var color2 = '#F8F8F8';//第二种颜色
    var tablename = document.getElementById(tableid)
    if (tablename != null) {
        var tr = tablename.getElementsByTagName("tr")
        for (var i = 1; i < tr.length; i++) {
            tr[i].onmouseover = function() {
                this.style.backgroundColor = overcolor;
            }
            tr[i].onmouseout = function() {
                if (this.rowIndex % 2 == 0) {
                    this.style.backgroundColor = color1;
                } else {
                    this.style.backgroundColor = color2;
                }
            }
            if (i % 2 == 0) {
                tr[i].className = "color1";
            } else {
                tr[i].className = "color2";
            }
        }
    }
}
onloadEvent(showtable);


var lastobj1 = null;
var lastobj2 = null;
function showMenu(obj1name, obj2name, obj2index) {
    if (lastobj1 != null && lastobj2 != null) {
        document.getElementById(lastobj1).className = "";
        document.getElementById(lastobj2).style.display = "none";
    }
    document.getElementById(obj1name + obj2index).className = "curr";
    document.getElementById(obj2name + obj2index).style.display = "block";
    lastobj1 = obj1name + obj2index;
    lastobj2 = obj2name + obj2index;
}