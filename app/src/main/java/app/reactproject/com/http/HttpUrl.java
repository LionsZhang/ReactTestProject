package app.reactproject.com.http;

/**
 * Created by lion on 2016/8/25.
 */
public class HttpUrl {
    /**
     * 获取短信验证码
     */
    public static String SMS_VERIFY_CODE = "common.send.sms.code";
    /**
     * 检验手机号是否注册
     */
    public static String CHECK_PHONE = "common.mobile.valid";
    /**
     * 检验短信验证码是否有效
     */
    public static String CHECK_VERIFY_CODE = "common.sms.code.valid";
    /**
     * 注册
     */
    public static String REGISTER = "user.reg";
    public static String REGISTER_RSA = "user.reg.rsa";
    /**
     * 登录
     */
    public static String LOGIN = "user.login";
    public static String LOGIN_RSA = "user.login.rsa";

    /**
     * 设置新登录密码
     */
    public static String RESET_PASSWARD = "user.reset.passwd";
    public static String RESET_PASSWARD_RSA = "user.reset.passwd.rsa";
    /**
     * 获取用户信息
     */
    public static String USER_INFO = "user.account.outline.info";
    /**
     * 获取我的頁面數據
     */
    public static String MINE_DATA_INFO = "user.app.my.index";

    /**
     * banner条接口
     */
    public static String BANNER_LIST = "banner.list";

    /**
     * 首页公告接口
     */
    public static String HOMEPAGE_NITICE = "announce.list";

    /**
     * 首页数据统计接口
     */
    public static String HOMEPAGE_GETINDEXCOUNT = "homepage.getindexcount";

    /**
     * 债权列表接口
     */
    public static String BORROW_PAGEDEBT_LIST = "borrow.pagedebt.list";

    /**
     * 投资记录
     */
    public static String BORROW_INVEST_LIST = "borrow.invest.list";

    /**
     * 理财产品列表
     */
    public static String APP_BORROW_PRODUCT_LIST = "v3_app.borrow.product.list";

    /**
     * 债券详情
     */
    public static String BORROW_DEBT_DETAIL = "v2_borrow.debt.detail";

    /**
     * 标的详情
     */
    public static String BORROW_DETAIL = "v4_borrow.detail";

    /**
     * 累计收益
     */
    public static String USER_ACCUMULATED_INCOME_LIST = "user.accumulated.income.list";

    /**
     * 验证手势密码
     */
    public static String VERIFY_GESTURE_PSW = "user.valid.gesture.passwd";
    /**
     * 设置手势密码
     */
    public static String SET_GESTURE_PSW = "user.set.gesture.passwd";
    /**
     * 获取设置数据
     */
    public static String GET_SET_DATA = "user.setting.index";

    /**
     * 我的红包列表
     */
    public static String COUPON_RED_PACKET_LIST = "coupon.red.packet.list";

    /**
     * 我的现金券列表
     */
    public static String COUPON_CASH_GIFT_LIST = "v3_coupon.cash.gift.list";

    /**
     * 获取银行卡列表信息
     */
    public static String GET_BANK_LIST = "user.get.bank.info";
    /**
     * 绑定银行卡
     */
    public static String BIND_BANK = "user.bind.bank.card01";
    public static String BIND_BANK_RSA = "user.bind.bank.card01.rsa";
    /**
     * 绑定银行卡
     */
    public static String BIND_BANK_TWO = "user.bind.bank.card02";

    /**
     * 首页列表
     */
    public static String APP_BORROW_RECOMMEND = "v5_app.borrow.recommend";
    /**
     * 设置交易密码
     */
    public static String SET_TRADE_PSW = "user.set.trade.passwd";
    public static String SET_TRADE_PSW_RSA = "user.set.trade.passwd.rsa";
    /**
     * 设置老用户交易密码
     */
    public static String SET_OLD_USER_TRADE_PSW = "user.set.trade.passwdsafe";
    /**
     * 修改交易密码
     */
    public static String MOTIFY_TRADE_PSW = "user.update.trade.passwd";
    public static String MOTIFY_TRADE_PSW_RSA = "user.update.trade.passwd.rsa";
    /**
     * 重置交易密码验证
     */
    public static String RESET_TRADE_PSW_VERIFY = "user.reset.valid.trade.passwd";
    /**
     * 重置交易密码
     */
    public static String RESET_TRADE_PSW = "user.reset.trade.passwd";
    public static String RESET_TRADE_PSW_RSA = "user.reset.trade.passwd.rsa";
    /**
     * 验证old登录密码
     */
    public static String VERIFY_OLD_LOGIN_PSW = "user.valid.passwd";
    public static String VERIFY_OLD_LOGIN_PSW_RSA = "user.valid.passwd.rsa";
    /**
     * 验证old交易密码
     */
    public static String VERIFY_OLD_TRADE_PSW = "user.valid.trade.passwd";
    public static String VERIFY_OLD_TRADE_PSW_RSA = "user.valid.trade.passwd.rsa";
    /**
     * 修改登录密码
     */
    public static String MOTITY_LOGIN_PASSWD = "user.update.passwd";
    public static String MOTITY_LOGIN_PASSWD_RSA = "user.update.passwd.rsa";


    /**
     * 用户可用余额
     */
    public static String USER_GET_BALANCE = "user.get.balance";
    /**
     * 查询现金券
     */
    public static String BORROW_MATCH_INFO = "v2_borrow.match.info";
    /**
     * 购买标的
     */
//    public static String BORROW_PURCHASE = "borrow.purchase";
//    public static String BORROW_PURCHASE_RSA = "borrow.purchase.rsa";
    public static String BORROW_PURCHASE_XW = "borrow.purchase.xw";
    public static String BORROW_PURCHASE_RSA = "borrow.purchase.rsa";
    /**
     * 购买债权
     */
    public static String BORROW_DEBT_BUY = "borrow.debt.buy";
    public static String BORROW_DEBT_BUY_RSA = "borrow.debt.buy.rsa";
    public static String BORROW_DEBT_BUY_XW = "xw.borrow.debt.buy";

    /**
     * 理财产品子列表
     */
    public static String APP_BORROW_PRODUCT_PAGE = "v3_app.borrow.product.page";
    /**
     * 拆红包接口
     */
    public static String COUPON_OPEN_RED_PACKET = "coupon.open.red.packet";
    /**
     * 我的--可用余额--账户明细
     */
    public static String APP_FUNDS_PAGE_LIST = "app.funds.page.list";
    /**
     * 充值获取银行卡信息
     */
    public static String FILL_GET_BIND_BANK_CARD_INFO = "pay.to.recharge";
    /**
     * 充值下单
     */
    public static String FILL_GET_ODER = "pay.api.order";
    /**
     * 充值支付
     */
    public static String FILL_PAY = "pay.api.pay";
    /**
     * 总资产
     */
    public static String USER_INVEST_AGGREGATE = "user.invest.aggregate";
    /**
     * 提现信息
     */
    public static String CREDIT_INFO = "v3_pay.to.withdrawal";
    /**
     * 提现
     */
    public static String CREDIT = "pay.withdrawal";
    /**
     * 获得通用配置参数
     */
    public static String COMMON_CONFIG = "common.config";
    /**
     * 退出登录接口
     */
    public static String USER_LOGOUT = "user.logout";
    /**
     * 获取消息
     */
    public static String MESSAGE_MY_LIST = "message.my.list";

    /**
     * 读取消息状态
     */
    public static String MESSAGE_UPDATE_STATUS = "message.update.status";

    /**
     * 好友列表
     */
    public static String RECOMMEND_FRIEND_PAGE_LIST = "recommend.friend.page.list";

    /**
     * 预回款列表统计
     */
    public static String APP_USER_REPAYMENT_COUNT = "app.user.repayment.count";

    /**
     * 预回款列表
     */
    public static String APP_USER_REPAYMENT_LIST = "app.user.repayment.list";

    /**
     * 我的投资
     */
    public static String APP_USER_PAGE_INVEST_LIST = "app.user.page.invest.list";

    /**
     * 我的投资 可转让统计
     */
    public static String APP_USER_DEBT_COUNT = "app.user.debt.count";

    /**
     * 意见反馈
     */
    public static String USER_QUESTION_SUGGESTION = "user.question.suggestion";

    /**
     * 转让债权初始化
     */
    public static String BORROW_DEBT_PURCHASE = "borrow.debt.purchase";

    /**
     * 转让债权
     */
    public static String BORROW_DEBT_ADD = "borrow.debt.add";
    public static String BORROW_DEBT_ADD_RSA = "borrow.debt.add.rsa";
    public static String BORROW_DEBT_ADD_XW = "xw.borrow.debt.add";

    /**
     * 收益汇总
     */
    public static String RECOMMEND_INCOME_AGGREGATE = "recommend.income.aggregate";
    /**
     * 获取图形验证码
     */
    public static String GET_PICTURE_VERIFY = "common.verification.code";
    /**
     * 获取图形验证码
     */
    public static String CHECK_PICTURE_VERIFY = "common.code.valid";
    /**
     * 校验token
     */
    public static String CHECK_TOKEN = "common.token.valid";
    /**
     * 获取分享内容
     */
    public static String getShareContent = "user.share.friend.circle";
    /**
     * 验证身份证
     */
    public static String USER_VALID_IDNO = "user.valid.IdNo";

    /**
     * 获取APP版本信息
     */
    public static String GET_APP_VERSION_INFO = "v3_app.get.version";
    /**
     * 获取公告或者活动信息
     */
    public static String GET_ANOUNCEMENT_OR_ACTIVITY_POP_INFO = "v2_homepage.pop";
    /**
     * 获取理财子列表头部图片
     */
    public static String GET_BORROW_CATE_EXTEND = "get.borrow.cate.extend";
    /**
     * 查询现金券列表信息
     */
    public static String QUERY_CASH_COUPON_LIST = "v3_borrow.match.info";
    /**
     * 预期收益
     */
    public static String BORROW_EXPECT_PROFIT = "v4_borrow.expect.profit";
    /**
     * 获取子列表在售个数
     */
    public static String BORROW_CATEGORY_LIST = "v4_borrow.category.list";
    /**
     * 卡券项数量
     */
    public static String COUPON_CASH_GIFT_COUN = "v4_coupon.cash.gift.count";
    /**
     * app还款计划V3
     */
    public static String V3_APP_REPAYMENT_PLAN = "v3_app.repayment.plan";
    /**
     * 续投设置初始化V3
     */
    public static String V3_CONTINUED_INVESTMENT_INIT = "v3_continued.investment.init";
    /**
     * 续投设置v3
     */
    public static String V3_CONTINUED_INVESTMENT_SET = "v3_continued.investment.set";
    /**
     * 待结奖励V3
     */
    public static String V3_PLAT_CUSTOMER_COUPON_LIST = "v3_plat.customer.coupon.list";
    /**
     * 查询卡券校验V3
     */
    public static String V3_BORROW_MATCH_VERIFY = "v3_borrow.match.verify";

    /**
     * 卡券使用说明
     */
    public static String COUPON_INSTRUCTION = "coupon.instruction";

    /**
     * 浮动图标
     */
    public static String FLOATING_LCON = "floating.lcon";

    /**
     * 活动弹窗
     */
    public static String ACTIVITY_PROMPT = "activity.prompt.box";

    /**
     * 开通存管
     */
    public static String OPEN_DEPOSITORY_ACCOUNT = "user.open.depository.account";
    /**
     * 激活存管
     */
    public static String ACTIVATE_DEPOSITORY_ACCOUNT = "user.activate.depository.account";
    /**
     * 充值
     */
    public static String DEPOSITORY_PAY_RECHARGE = "pay.recharge";
    /**
     * 提现
     */
    public static String DEPOSITORY_PAY_CREDIT = "pay.depository.withdraw";
    /**
     * 解绑银行卡
     */
    public static String UNBIND_BANKCARD = "user.unbind.bankcard";
    /**
     * 绑定银行卡
     */
    public static String BIND_BANKCARD = "user.bind.bankcard";
    /**
     * 修改交易密码
     */
    public static String UPDATE_TRANSACTION_PASSWORD = "user.update.transaction.password";
    /**
     * 修改预留手机号
     */
    public static String UPDATE_RESERVED_MOBILE = "user.update.reserved.mobile";
    /**
     * 验证身份证
     */
    public static String VERIFY_LICENCE = "user.valid.IdNo";

    /**
     * 充值结果查询
     */
    public static String PAY_RECHARGE_RESULT = "pay.recharge.result";
    /**
     * 提现结果查询
     */
    public static String PAY_WITHDRAW_RESULT = "pay.withdraw.result";
    /**
     * 开通存管账户结果查询
     */
    public static String RESULT_OPEN_DEPOSITORY_ACCOUNT = "user.open.depository.account.result";
    /**
     * 激活存管账户结果查询
     */
    public static String RESULT_ACTIVATE_DEPOSITORY_ACCOUNT = "user.activate.depository.account.result";
    /**
     * 绑定银行卡结果查询
     */
    public static String RESULT_BIND_BANKCARD = "user.bind.bankcard.result";
    /**
     * 解绑银行卡结果查询
     */
    public static String RESULT_UNBIND_BANKCARD = "user.unbind.bankcard.result";
    /**
     * 修改预留手机号结果查询
     */
    public static String RESULT_UPDATE_RESERVED_MOBILE = "user.update.reserved.mobile.result";

    /**
     * 充值交易密码结果查询
     */
    public static String RESULT_UPDATE_TRANSACTION_PASSWORD = "user.update.transaction.password.result";
    /**
     * 购买标的结果查询
     */
    public static String RESULT_PURCHASE_XW = "borrow.purchase.xw.result";

    /**
     * 购买债权的结果查询
     */
    public static String XW_QUERY_BUY_DEBT_RESULT = "xw.query.buy.debt.result";

    /**
     * 查询存管开通情况
     */
    public static String QUERY_OPEN_DEPOSITORY_STATUS = "query.open.depository.status";



    /**
     * 用户是否续投过--是否弹框
     */
    public static String IS_CONTINUED_FLAG = "is.continued.flag";
    /**
     * md5校验
     */
    public static String COMMON_MD5_VALIDATE = "common.md5.validate";
}
