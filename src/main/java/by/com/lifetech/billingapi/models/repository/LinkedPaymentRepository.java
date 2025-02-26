package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.LinkedPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkedPaymentRepository extends JpaRepository<LinkedPayment, String> {

    @Query(value = """
                SELECT fh.code,
                     fh.entry_date,
                     fh.audit_date,
                     fh.type_code,
                     fh.reason_code,
                     fh.amount,
                     fh.mobile_no,
                     fh.contract_code,
                     fh.document_no,
                     (SELECT 'S'
                        FROM tm_fm.financial_history s
                       WHERE s.linked_code = fh.code
                         AND s.type_code    IN ('JY', 'JD')
                      ) AS status,
                     fh.agent_name,
                     fh.reference,
                     fh.comments,
                     fop.payment_agent_code,
                     fop.payment_subagent_code,
                     fop.transaction_id,
                     foa.payment_tool_type_code,
                     foa.legal_person_unn,
                      CASE
                        WHEN (foa.erip_service_code = '2')
                        THEN 'PD_PENALTY'
                        ELSE NULL
                      END AS payment_desc_code,
                     fop.payment_string
                FROM tm_fm.financial_history fh
                left outer join tm_fm.fm_offline_payments fop on fh.code = fop.financial_history_code
                left outer join tm_fm.fm_offline_attributes foa on fop.code = foa.offline_payment_code
                WHERE fh.code in (select source
                                 from tm_fm.fm_fh_operation_links fol
                                 connect by prior source = destination
                                 start with destination = :code)
                order by fh.code desc
            """, nativeQuery = true)
    List<LinkedPayment> getLinkedPayments(
            @Param("code") String code);
}