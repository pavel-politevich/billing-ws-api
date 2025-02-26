package by.com.lifetech.billingapi.utils;

public final class SqlConstants {
        public static final String MULTI_GROUP_SQL = """
            select t.*, (t.balance_name||t.msisdn||t.type) as key,
              d_bal.name_ru as BALANCE_NAME_RU,
              d_st.name_ru as STATE_NAME_RU,
              d_pl.name_ru as PL_NAME_RU
            from table(tm_cim.IUI3.get_multi_group_data(?)) t
              left join conf.DICT_MULTI_GROUPS d_bal on t.balance_name = d_bal.code
              left join conf.DICT_MULTI_GROUPS d_st on t.status = d_st.code
              left join conf.DICT_MULTI_GROUPS d_pl on t.pocket_label = d_pl.code
            """;

    public static final String OWN_GROUP_INF = """
            select nvl(a.account_name, '') msisdn
             , null as owner_msisdn
             , gr.name_ru, gr.discount, gr.suspend_date, gr.leave_date
             from
             (
             select
                  u.account_id
                  , d.name_ru
                  , decode(u.discount,'','',(1-u.discount)*100 || '%') as discount
                  , u.suspend_date
                  , u.suspend_date + 60 as leave_date
                  , u.enter_date
             from tm_om.OWN_GROUP_USERS u
             left join conf.dict_own_group_state d on u.state_code = d.code
               where 1=1
               and u.group_id =
               (
                 select max(t.group_id) from tm_om.OWN_GROUP_USERS t where t.account_id = ?
               )
               and u.state_code in ('OWNER','USER')
               and u.leave_date is null
             ) gr
              join bwsapi.account_tbl a on gr.account_id = a.account_id
           """;

    public static final String OWN_GROUP_HST = """
            select nvl(a.account_name, '') msisdn
             , null as owner_msisdn
             , gr.name_ru, gr.enter_date, gr.leave_date
             from
             (
             select
                  u.account_id
                  , d.name_ru
                  , u.leave_date
                  , u.enter_date
             from tm_om.OWN_GROUP_USERS u
             left join conf.dict_own_group_state d on u.state_code = d.code
               where 1=1
               and u.group_id =
               (
                 select max(t.group_id) from tm_om.OWN_GROUP_USERS t where t.account_id = ?
               )
               and ( u.state_code in ('OWNER','USER','DELETED')
                      or (u.state_code = 'INVITED' and u.invite_date >= sysdate -2)
                    )
             ) gr
              join bwsapi.account_tbl a on gr.account_id = a.account_id
            """;

    public static final String COMPENSATION_LIMIT_SQL = """
            select ta.refund_limit - nvl(tb.sum_spent,'0')  REMAINS_LIMIT
            from tm_cim.LOSS_COMPENS ta left join
            	(select th.id_contr,sum(th.spent_limit) sum_spent from  tm_cim.LOSS_COMPENS_HIST th
            	where th.spent_date> sysdate - interval '12' month
            	group by th.id_contr
            	) tb
            on ta.id_contr = tb.id_contr
            where ta.id_contr = ?
            """;

    public static final String GET_TOP_ID_BY_CONTRACT_SQL = """
            select j.account_id
            from bwsapi.account_tbl j where j.account_hierarchy_fk in (
                select h.account_hierarchy_fk from bwsapi.account_tbl h where h.top_level_account_id in (
                   select t.account_fk from bwsapi.account_ex_tbl t
                   where lower(CONTRACT_ID) = lower(?) and t.contract_type='CORP' ))
            and j.account_type='CCO' and j.LC_STATE <> 'TRM'
            """;

    public static final String GET_PASSPORT_TYPE_BY_ID_SQL = """
            select ext.PASSPORT_TYPE from bwsapi.account_ex_tbl ext
            where ext.ACCOUNT_FK =(
            select h.TOP_LEVEL_ACCOUNT_ID from bwsapi.account_tbl h where h.ACCOUNT_ID = ? )
            """;

    private SqlConstants() {
    }
}
