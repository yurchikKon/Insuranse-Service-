package com.yura0.insuranceService.Services;

import com.yura0.insuranceService.Configuration.HibernateUtil;
import com.yura0.insuranceService.DAO.PretensionDAO;
import com.yura0.insuranceService.DAO.UserDAO;
import com.yura0.insuranceService.Entities.InsType;
import com.yura0.insuranceService.Entities.Insurance;
import com.yura0.insuranceService.Entities.Pretension;
import com.yura0.insuranceService.Entities.User;
import com.yura0.insuranceService.Requestes.PretensionRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PretensionService {

    private final PretensionDAO pretensionDAO;
    public final UserDAO userDAO;
    private final EmailServiceImpl emailService;

    @Transactional
    public boolean correctInsType(List<Insurance> list, InsType insType){
        return list.stream().anyMatch((Insurance ins) -> ins.getInsType() == insType);
    }

    @Transactional
    public ResponseEntity<?> createPretension(User user, PretensionRequest pretensionRequest){
        InsType insType = null;
        if(pretensionRequest.getType().equals("CAR"))
            insType = InsType.CAR;
        else if(pretensionRequest.getType().equals("EXTRA"))
            insType = InsType.EXTRA;
        else insType = InsType.BASE;
        if(!correctInsType(user.getInsurances(),insType)) return ResponseEntity.ok("You dont have necessary insurance");
        Pretension pretension = Pretension.builder()
                .title(pretensionRequest.getTitle())
                .text(pretensionRequest.getText())
                .usr(user)
                .employee(userDAO.getCurrEmployee())
                .insType(insType)
                .build();
        pretensionDAO.save(pretension);
        return ResponseEntity.ok("New pretension was created");
    }

    public ResponseEntity<?> showPretensions(UserDetails userDetails){
        Optional<User> user = userDAO.getByLogin(userDetails.getUsername());
        List<Pretension> list = pretensionDAO.getByUsrId(user.get().getId());
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<?> showTasks(UserDetails userDetails){
        Optional<User> user = userDAO.getByLogin(userDetails.getUsername());
        List<Pretension> list =pretensionDAO.getByEmployeeId(user.get().getId());
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<?> answer(Long id, String answer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query queryAns = session.createQuery("update Pretension" +
                    " set answer = :answerParam" +
                    " where id = :idParam");
            queryAns.setParameter("answerParam", answer);
            queryAns.setParameter("idParam", id);
            queryAns.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        System.out.println(pretensionDAO.get(id).get().getUsr().getLogin());
        emailService.send(pretensionDAO.get(id).get().getUsr().getMail(),
                "Pretension request","Answer on your pretension has already been prepared");
        return ResponseEntity.ok("Update success");
    }

    public ResponseEntity<?> drop(User user) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("update pretensions " +
                    "set employee_user_id = null " +
                    "where employee_user_id = :idParam and answer is not null");
            System.out.println(user.getId());
            query.setParameter("idParam", user.getId());
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return ResponseEntity.ok("Pretensions dropped");
    }
}
