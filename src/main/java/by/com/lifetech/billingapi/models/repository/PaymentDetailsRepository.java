package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, String> {

    @Query(value = """
				SELECT fhv.code,
					  fhv.payment_agent_code,
					  case when (eot.FINANCIAL_HISTORY_CODE = fhv.code) then eot.subagent else fhv.payment_subagent_code end as payment_subagent_code,
					  case when (eot.FINANCIAL_HISTORY_CODE = fhv.code) then eot.transaction_id else fhv.transaction_id end as transaction_id,
					  fhv.payment_tool_type_code,
					  fhv.legal_person_unn,
					  fhv.payment_desc_code,
					  fhv.file_string,
					  fhv.document_no,
					  fhv.bank_branch,
					  fhv.payer_name,
					  fhv.comments
				FROM tm_fm.financial_history_v fhv
				left join tm_fm.erip_online_transactions eot on eot.FINANCIAL_HISTORY_CODE = fhv.code
				WHERE fhv.code = :code
				and trunc(fhv.entry_date, 'DAY') = trunc(:entryDate, 'DAY')
			""", nativeQuery = true)
    Optional<PaymentDetails> getPaymentDetails(
            @Param("entryDate") LocalDateTime entryDate,
            @Param("code") String code);
}