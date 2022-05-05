package kz.alizhan.lib.Repository;

import kz.alizhan.lib.Entity.Dialog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.Optional;

@Repository
public interface DialogRepository extends CrudRepository<Dialog,Long> {

    @Query(value = "select dialog_id from dialog where chat_id=:chatId",nativeQuery = true)
    Optional<Integer> getDialogId(Long chatId);

    @Transactional
    @Modifying
    @Query(value = "delete from dialog where chat_id=:chatId",nativeQuery = true)
    void deleteDialogByChatId(Long chatId);

}
