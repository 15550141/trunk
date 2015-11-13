var chatMap = new Map();
var allIntervalId, oneIntervalId;

String.prototype.a2b = function() {
    var A = arguments;
    return this.replace(/\{(\d+)\}/g, function(a, b) {
        return A[b - 1] === void (0) ? '' : A[b - 1]
    });
}

var tmpKey = "{1}_{2}";
/**
 * 实例一个新的Options
 *
 * @param id
 * @param onloadFunc
 */
function createOptions(id, onloadFunc) {
    var newOptions = {
        chart : {
            renderTo : id,
            type : 'spline',
            marginRight : 10,
            events : {
                load : function() {
                    if (onloadFunc != undefined
                            && typeof onloadFunc == "function") {
                        setInterval(onloadFunc(), 30000);
                    }
                }
            }
        },
        rangeSelector : {
            enabled : false,
            inputEnabled : false,
            selected : 0
        },
        title : {
            text : 'Live random data',
            useHTML : true
        },
        xAxis : {
            type : 'datetime',
            tickPixelInterval : 150
        },
        yAxis : {
            min : 0,
            title : {
                text : 'Value'
            },
            plotLines : [
                {
                    value : 0,
                    width : 1,
                    color : '#808080'
                }
            ]
        },
        tooltip : {
            crosshairs : true,
            shared : true,
            xDateFormat: "时间:　%H:%M"
        },
        plotOptions : {
            spline : {
                marker : {
                    radius : 4,
                    lineWidth : 0,
                    enabled: true
                }
            }
        },
        legend : {
            enabled : true
        },
        navigator : {
            top : 295
        },
        exporting : {
            enabled : true
        },
        series : []
    };
    chatMap.put(id, newOptions);
    return newOptions;
}

/**
 * 实例一个新的Options
 *
 * @param id
 * @param onloadFunc

 */
function createOptions4WeekMonth(id, title) {
    var newOptions = {
        chart : {
            renderTo : id,
            type : 'spline',
            marginRight : 10
        },
        rangeSelector : {
            enabled : false,
            inputEnabled : false,
            selected : 0
        },
        title : {
            text : 'Live random data',
            useHTML : true
        },
        xAxis : {
            type : 'datetime',
            tickPixelInterval : 150
        },
        yAxis : {
            min : 0,
            title : {
                text : 'Value'
            },
            plotLines : [
                {
                    value : 0,
                    width : 1,
                    color : '#808080'
                }
            ]
        },
        tooltip : {
            crosshairs : true,
            shared : true,
            pointFormat:"<span style=\"color:{series.color}\">{point.name}</span>: <b>{point.y}</b><br/>",
            xDateFormat: title
        },
        plotOptions : {
            spline : {
                marker : {
                    radius : 4,
                    lineWidth : 0,
                    enabled: true
                }
            }
        },
        legend : {
            enabled : true
        },
        navigator : {
            top : 295
        },
        exporting : {
            enabled : true
        },
        series : []
    };
    return newOptions;
}

/**
 * 判断是否为全部的Options
 *
 * @param curOption

 */
function getOption(curOption) {

    return (curOption == undefined || curOption == null) ? options : curOption;
}

function topHide(bool) {

    if (bool) {
        $('#topMenu').hide();
        $('#topMenu').removeClass('top');
    } else {
        $('#topMenu').show();
        $('#topMenu').addClass('top');
    }
}

/**
 * 初始化/重置图表
 *
 * @param name
 * @param rftype

 */
function initTable(name, rftype, minute) {

    var url = getUrl(name, rftype, 'report/newReg_showCount');
    var dataResult = load(url + '&queryVO.init=1');
    if (dataResult == null) {
        alert('数据加载错误');
        return;
    }
    hideDiv(true);
    initOthers();
    parameter.put('name', name);
    parameter.put('minute', minute);
    parameter.put('rftype', rftype);
    parameter.put('showDetail', '1');
    options.series = [];
    pushData(dataResult, true);
    topHide(false);
    clearAllInterval();
        resetMinutes(dataResult.minute);
    oneIntervalId = setInterval(oneTypeNext, 30000);
}

/**
 * 初始化/重置图表
 *
 * @param name
 * @param rftype

 */
function initTableNew(dataArr) {

    hideDiv(true);
    initOthers();
    parameter.put('name', dataArr.name);
    parameter.put('minute', dataArr.minute);
    parameter.put('rftype', dataArr.rftype);
    parameter.put('showDetail', '1');

    options.series = [];
    pushData(dataArr, true);
    topHide(false);

    resetMinutes(dataArr.minute);
    clearAllInterval();
    oneIntervalId = setInterval(oneTypeNext, 30000);
}

/**
 * 初始化所有参数, 供新的查询请求重置
 *

 */
function initOthers() {
    parameter = new Map();
    dayMap = new Map();
    lastMinutes = '';
    time = 0;
    tableName = '';
}

/**
 * 给当前图表增加实例
 *
 * @param data
 * @param isCreate
 * @param curOptions

 */
function pushData(data, isCreate, curOptions) {

    var isOne = (parameter.get('showDetail') == '1');
    var reversed = false;
    var tmpOptions = getOption(curOptions);
    if (tmpOptions.series == null && isOne) {
        tmpOptions = chart.options;
    }
    // 今天的数据, 只有初始化 或者刷新的时候 才会有
    if (isNotNull(data.todayList)) {
        addSeries(data.todayList, true, tmpOptions);
    }

    // 其他指定日期或者是初始化时 昨天的数据集合
    if (isNotNull(data.yesterdayList)) {

        addSeries(data.yesterdayList, false, tmpOptions);
    }

    // 其他指定日期或者是初始化时 昨天的数据集合
    if (isNotNull(data.weekList)) {
        addSeries(data.weekList, false, tmpOptions);
    }

    // 本周或本月 的数据, 只有初始化 或者刷新的时候 才会有
    if (isNotNull(data.weekMonthList)) {
        reversed = true;
        addWeekMonthSeries(data.weekMonthList, true, tmpOptions);
    }

    // 其他指定日期或者是初始化时 上周或上月的数据集合
    if (isNotNull(data.otherWeekMonthList)) {
        reversed = true;
        addWeekMonthSeries(data.otherWeekMonthList, false, tmpOptions);
    }

    clearWeekMonth(reversed);
    lastMinutes = data.lastMinute;
    // if(lastSeries != null) {
    // tmpOptions.series.push(lastSeries);
    // }
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    tmpOptions.title.text = "<div style='border:1px solid #ccc; display:block; background:rgb(19,130,185); color:white;font-weight:bold;width:200px; height:25px;'><a style='cursor:pointer;' onclick=\"javascript:initTable('"
            + data.name
            + "','"
            + data.rftype
            + "','"
            + data.minute
            + "');\">"
            + transMap.get(data.rftype) + "</a></div>";

    if (isCreate != undefined && isCreate) {

        chart = isOne ? new Highcharts.StockChart(tmpOptions)
                : new Highcharts.Chart(tmpOptions);

        if (isOne) {

            put('chart', chart);
            put('options', tmpOptions);
        }
        chatMap.put(data.rftype, chart);
    }
}

/**
 * 按周展现
 * @param year
 * @param week

 */
function initWeek(year, week) {
    var url = 'report/newReg_searchWeek.action?queryVO.rftype=' + parameter.get('rftype');
    var init = isNotNull(year) && isNotNull(week);

    //如果不是初始化, 则拼接要添加的年, 周
    if (init) {
        url += "&queryVO.year=" + year + "&queryVO.week=" + week;
    }
    clearWeekMonth(0);

    var result = load(url);
    if (options2 != undefined && options2.series != undefined) {
        options2.series = [];
    }
    options2 = createOptions4WeekMonth('container', '按周环比');
    pushData(result, !init, options2);

    //当前默认的为 上一周
    putFlag(queryWeekMap, result.year, result.week);
    //本周的需要在上一周的基础上加1
    putFlag(queryWeekMap, result.year, parseInt(result.week) + 1);
}

function clearWeekMonth(bool) {

    if (!bool) {

        parameter.drop('week_month');
        queryWeekMap = new Map();
        queryMonthMap = new Map();
    }
}
/**
 * 按月展现
 * @param year
 * @param month

 */
function initMonth(year, month) {
    var url = 'report/newReg_searchMonth.action?queryVO.rftype=' + parameter.get('rftype');

    var init = isNotNull(year) && isNotNull(month);
    if (init) {
        url += "&queryVO.year=" + year + "&queryVO.month=" + month;
    }
    clearWeekMonth(0);

    parameter.put('week_month', "按月环比");
    var result = load(url);
    if (options2 != undefined && options2.series != undefined) {
        options2.series = [];
    }
    options2 = createOptions4WeekMonth('container', '按月环比');
    pushData(result, !init, options2);

    //当前默认的为 上一月
    putFlag(queryMonthMap, result.year, result.month);
    //本月的需要在上一月的基础上加1
    putFlag(queryMonthMap, result.year, parseInt(result.month) + 1);
}

/**
 * 放入加载过的标记
 * @param maps
 * @param year
 * @param monthOrWeek

 */
function putFlag(maps, year, monthOrWeek) {
    maps.put(tmpKey.a2b(year, monthOrWeek), "1");
}

/**
 * 按周, 按月的Series
 * @param dataArray
 * @param bool
 * @param curOptions

 */
function addWeekMonthSeries(dataArray, bool, curOptions) {
    var series = createWeekMonthSeries(dataArray, bool);

    var tmpOptions = getOption(curOptions);

    tmpOptions.series.push(series);
}

/**
 * 按周, 按月 构建指定数据集合的Series
 *
 * @param dataArray
 * @param bool

 */
function createWeekMonthSeries(dataArray, bool) {
    var innerTime;
    var series = {
        data : []
    };
    for (var i = 0; i < dataArray.length; i++) {

        var obj = dataArray[i];

        if (i == 0) {
            var innerDate = new Date();
            innerTime = innerDate.getTime() - (innerDate.getHours() * 3600 + innerDate.getMinutes() * 60 + innerDate.getSeconds()) * 1000 - innerDate.getMilliseconds();
            if (bool != undefined && bool) {
                //					innerTime = Date.parse(obj.date);
            }
            series.name = obj.date + ' 至 ' + dataArray[dataArray.length - 1].date;
        }


        tableName = obj.date;
        series.data.push({
            x : innerTime + obj.minutes * 24 * 60 * 60 * 1000,
            y : obj.count,
            name : obj.date
        });
    }
    return series;
}
/**
 * 把数据集合Series Push到options.series
 *
 * @param dataArray
 * @param bool
 * @param curOptions
 */
function addSeries(dataArray, bool, curOptions) {


    var series = createSeries(dataArray, bool);

    // series.name = tableName;

    var tmpOptions = getOption(curOptions);

    tmpOptions.series.push(series);
    dayMap.put(tableName, tableName);
}

/**
 * 构建指定数据集合的Series
 *
 * @param dataArray
 * @param bool

 */
function createSeries(dataArray, bool) {
    var series = {
        data : []
    };
    var temp = 10;
    if (parameter.get("minute") != undefined && parameter.get("minute") != null)
        temp = parameter.get("minute");
    for (var i = 0; i < dataArray.length; i++) {


        var obj = dataArray[i];

        if (i == 0) {

            if (bool != undefined && bool) {
                //				time = Date.parse(obj.date);
                date = new Date();
                time = date.getTime() - (date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds()) * 1000 - date.getMilliseconds();
                //				alert(new Date().getTime());
                //				time=time+new Date().getTimezoneOffset()*60000;
            }

            tableName = obj.date;
            series.name = tableName;

        }

        series.data.push({

            //x : time + (obj.minutes - temp) * 1000 * 60,
            x : time + (obj.minutes) * 1000 * 60,
            y : obj.count
        });
    }
    return series;
}
function createSeriesWzf(dataArray, series, bool) {
    // var series = {
    // data : []
    // };
    // var chart=series.chart;
    var count = parameter.get("count");
    var data = series.data;
    for (var i = 0; i < dataArray.length; i++) {
        var shift = (data.length >= (i + 1));
        var redraw = ((i + 1) == dataArray.length);
        // alert(shift);
        var obj = dataArray[i];
        if (i == 0) {
            if (bool != undefined && bool) {
                time = Date.parse(obj.date);
            }
            tableName = obj.date;
        }
        var x = time + obj.minutes * 1000 * 60;
        var y = obj.count;
        // series.data.push( {
        // x : time + obj.minutes * 1000 * 60,
        // y : obj.count
        // });
        series.addPoint([ x, y ], redraw, shift);
    }
    return series;
}
/**
 * 非空
 *
 * @param arr

 */
function isNotNull(arr) {
    return arr != undefined && arr != null && arr.length > 0;
}

/**
 * ajax load数据
 *
 * @param url

 */
function load(url) {
    var result = null;
    var append = url.indexOf('?') == -1 ? '?' : '&';
    $.ajax({
        url : url + append + 't=' + new Date().getTime(),
        type : "POST",
        dataType : "json",
        async : false,
        beforeSend : function() {
        },
        error : function(XMLHttpRequest) {

        },
        success : function(data, textStatus) {
            if (data != null) {

                result = data.perReportData;
            }
        },
        complete : function(XMLHttpRequest, textStatus) {
        }
    });
    return result;
}

function hideAll() {
    $('#weekTR').hide();
    $('#monthTR').hide();
    $('#dayTR').hide();
}

/**
 * 指定分钟数 重新获取数据
 *
 * @param mins

 */
function changeMinute(mins) {

    hideAll();
    if (mins.indexOf("_") != -1) {

        if (mins.indexOf("week") != -1) {
            $('#weekTR').show();
            initWeek();
        }
        if (mins.indexOf("month") != -1) {
            $('#monthTR').show();
            initMonth();
        }
        clearAllInterval();
        resetMinutes(mins);
        return;
    }
    $('#dayTR').show();
    var count = 30;
    if (mins == '5') {
        count = mins * 30;
    }
    if (mins == '10') {
        count = mins * 30;
    }
    if (mins == '30') {
        count = mins * 20;
    }
    if (mins == '60') {
        count = mins * 10;
    }
    parameter.put('count', count);
    parameter.put('minute', mins);

    resetMinutes(mins);
    refresh();
}

/**
 * 获取指定周数的数据
 * @param min

 */
function addWeek(needValid) {

    var year = $('#weekYear').val();
    var week = $('#week').val();

    var key = tmpKey.a2b(year, week);

    if (needValid && queryWeekMap.get(key) != null) {
        alert(year + '年  第' + week + '周  数据已经添加过, 请勿重复添加!');
        return;
    }
    clearAllInterval();
    var url = 'report/newReg_ajaxWeek.action?queryVO.year=' + year + '&queryVO.week=' + week + '&queryVO.rftype=' + parameter.get('rftype');
    var dataResult = load(url);

    if (dataResult == null || !isNotNull(dataResult.otherWeekMonthList)) {
        alert("没有获取到  " + year + "年 第" + week + "周 的数据!");
        return;
    }
    pushData(dataResult, true, options2);
    putFlag(queryWeekMap, year, week);
}

/**
 * 获取指定月份的数据
 * @param min

 */
function addMonth(needValid) {

    var year = $('#monthYear').val();
    var month = $('#month').val();

    var key = tmpKey.a2b(year, month);

    if (needValid && queryMonthMap.get(key) != null) {
        alert(year + '年  ' + month + '月  数据已经添加过, 请勿重复添加!');
        return;
    }
    clearAllInterval();
    var url = 'report/newReg_ajaxMonth.action?queryVO.year=' + year + '&queryVO.month=' + month + '&queryVO.rftype=' + parameter.get('rftype');
    var dataResult = load(url);

    if (dataResult == null || !isNotNull(dataResult.otherWeekMonthList)) {
        alert("没有获取到  " + year + "年 " + month + "月 的数据!");
        return;
    }
    pushData(dataResult, true, options2);
    putFlag(queryMonthMap, year, month);
}

/**
 * 将当前选中的分钟数 href属性删除, 为不可点击状态
 *
 * @param mins

 */
function resetMinutes(mins) {
    $("a[name=AMinute]").each(function() {
        var val = $(this).attr('id');
        if (val == mins) {
            $(this).removeAttr('href');
        } else {
            $(this).attr('href', 'javascript:changeMinute(\'' + val + '\')');
        }
    });
}

/**
 * 刷新单个图标的多线
 *

 */
function refresh() {
    var values = dayMap.getValues();
    options.series = [];
    var isCreate = false;
    if (chart != undefined || chart != null) {
        chart.options.series = [];
    }
    for (var i = 0; i < values.length; i++) {

        if (i == values.length - 1)
            isCreate = true;

        var url = createURL('report/newReg_ajaxLoad', values[i]);

        var dataResult = load(url);
        pushData(dataResult, isCreate);
    }
    clearAllInterval();
    oneIntervalId = setInterval(oneTypeNext, 30000);
}

/**
 * 添加一天的数据
 *
 * @param day

 */
function addOneDay(day) {
    if (isNull(day)) {
        alert('请选择添加的日期');
        $('#searchDate').click();
        return;
    }
    if (dayMap.get(day) != null || dayMap.get(day.replace("-", "")) != null) {
        alert('已经添加过[' + day + ']的数据, 请勿重复添加.');
        return;
    }
    var url = createURL('report/newReg_ajaxLoad', day);
    var dataResult = load(url);

    if (dataResult == null || !isNotNull(dataResult.yesterdayList)) {
        alert('没有查询到[' + day + ']的数据, 添加失败');
        return;
    }

    pushData(dataResult, true);
}

function isNull(obj) {

    return obj == null || obj == '' || obj.length == 0;
}

/**
 * 初始化所有options.series = []
 *

 */
function initOptionsMap() {

    var values = allOptions.getValues();
    for (var i = 0; i < values.length; i++) {
        values[i].series = [];
    }
}

/**
 * 显示隐藏图表container
 *
 * @param bool

 */
function hideDiv(bool) {

    if (bool)
        $('#container').show();
    else
        $('#container').hide();

    $('div[id^=container_]').each(function() {
        if (bool)
            $(this).hide();
        else
            $(this).show();
    });
}

/**
 * 初始化全部, 并隐藏topMenu
 *
 * @param type

 */
function initAllNew(dataArr) {

    hideDiv(false);
    topHide(true);

    parameter.put("minute", dataArr.minute);
    parameter.put('rftype',dataArr.rftype);
    var dataResult = dataArr; // 获取返回的数据

    var arr = dataResult.dataMap;
    var others = dataResult.yesterdayMap;
    var week = dataResult.weekMap;
    var index = 0;

    for (var i in arr) {
        var cOption = null;

        cOption = createOptions('container_' + index);

        allOptions.put(i, cOption);

        dataResult.todayList = arr[i];

        if (others[i] != undefined) {
            dataResult.yesterdayList = others[i];
        }
        if (week[i] != undefined) {
            dataResult.weekList = week[i];
        }

        dataResult.rftype = i;

        pushData(dataResult, true, cOption);
        index++;
    }
    clearAllInterval();
    allIntervalId = setInterval(doAllNext, 30000);
}

/**
 * 获取全部的跳点
 *
 */
function doAllNext() {
    var tmpResult = load('report/newReg_showAll.action?queryVO.minute=' + parameter.get('minute') + '&queryVO.lastMinute=' + lastMinutes+"&queryVO.rftype=" + parameter.get('rftype') );

    var arr = tmpResult.dataMap;
    var others = tmpResult.yesterdayMap;
    var week = tmpResult.weekMap;
    lastMinutes = tmpResult.lastMinute;
    for (var i in arr) {

        var tmpChart = chatMap.get(i);
        var tmpData = createSeries(arr[i], true);
        // createSeriesWzf(arr[i],tmpChart.series[0], true)
        tmpChart.series[0].setData(tmpData.data, true);
        if (others != 'null' && others != undefined && others[i] != undefined) {
            var otherData = createSeries(others[i], false);
            tmpChart.series[1].setData(otherData.data, true);
            // createSeriesWzf(others[i],tmpChart.series[1], false);
        }
        if (week != 'null' && week != undefined && week[i] != undefined) {
            var otherData = createSeries(week[i], false);
            tmpChart.series[2].setData(otherData.data, true);
        }
    }
}

/**
 * 单个类型下 所有时间的线条跳点更新
 *

 */
function oneTypeNext() {
    var tmpChart = get('chart');
    // alert('one next chart length : ' + tmpChart.series.length);
    var url = createURL('report/newReg_showOne') + '&queryVO.oneTypeDays=' + dayMap.getValues().join(",");
    if (parameter.get('minute') == '1') {
        url += '&queryVO.init=1';
    }
    var tmpResult = load(url);


    var others = tmpResult.yesterdayMap;
    lastMinutes = tmpResult.lastMinute;

    var tmpData = createSeries(tmpResult.todayList, true);
    tmpChart.series[0].setData(tmpData.data, true);

    for (var i in others) {
        var index = getSeriesIndex(i, tmpChart);
        var otherData = createSeries(others[i], false);
        tmpChart.series[index].setData(otherData.data, true);
    }
}

function getSeriesIndex(seriesName, chart) {

    for (var i = 0; i < chart.series.length; i++) {

        if (chart.series[i].name == seriesName) {
            return i;
        }
    }
}

/**
 * 创建全部参数的url
 *
 * @param actionName
 * @param date

 */
function createURL(actionName, date) {
    var day = date == null || date == undefined ? null : date;
    return getUrl(parameter.get('name'), parameter.get('rftype'), actionName,
            day, parameter.get('minute'), parameter.get('count'));
}
/**
 * 清除全局Interval
 *

 */
function clearAllInterval() {

    clearInterval(oneIntervalId);
    clearInterval(allIntervalId);
}

function addShowCount(counts) {

    if (isNull(counts)) {

        alert('请输入显示节点数');
        $('#showCounts').focus();
        return;
    }

    parameter.put('count', counts);
    refresh();
    $('#showCounts').val('');
}

function put(k, v) {
    parameter.put(k, v);
}

function get(k) {
    return parameter.get(k);
}

function isOne() {
    return parameter.get('showDetail') == '1';
}