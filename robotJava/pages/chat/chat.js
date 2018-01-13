Page({
    data: {
        msgList: [],
        scrollTop: 0
    },
    onLoad: function (options) {

    },
    send: function (e) {
        console.log(e.detail.value.msg);
        var msg = {"type": 0, "msg": e.detail.value.msg};
        var msgList = this.data.msgList;
        msgList.push(msg);
        this.setData({
            msgList: msgList
        });
        this.getReply(e.detail.value.msg);
    },
    getImg:function () {

    },
    getReply: function (sendMsg) {
        var that = this;//获取数据
        var url = "http://localhost:8080/RobotPractice/servlet";
        wx.request({
            url: url,
            data: {
                'info': sendMsg,
            },
            method: 'POST',
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            success: function (res) {
                console.log(res);
                var msg = {};
                var msgList = that.data.msgList;
                if (res.data.code === 100000) {
                    msg = {"type": 1, "msg": res.data.text,"code":res.data.code};
                    msgList.push(msg);
                    that.setData({
                        msgList: msgList
                    });
                    that.setData({
                        scrollTop: that.data.scrollTop +100
                    })
                } else if (res.data.code === 200000) {
                    msg = {"type": 1, "msg": res.data.text, "url": res.data.url.toString(),"code":res.data.code};
                    msgList.push(msg);
                    that.setData({
                        msgList: msgList
                    });
                    that.setData({
                        scrollTop: that.data.scrollTop +200
                    })

                } else if (res.data.code === 302000 || res.data.code === 308000) {
                    msg = {"type": 1, "msg": res.data.text, "list": res.data.list,"code":res.data.code};
                    msgList.push(msg);
                    that.setData({
                        msgList: msgList
                    });
                    that.setData({
                        scrollTop: that.data.scrollTop +400
                    })
                }
                console.log(that.data.scrollTop);
            }
        })

    }
})