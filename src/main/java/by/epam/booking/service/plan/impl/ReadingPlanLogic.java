package by.epam.booking.service.plan.impl;

import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.exception.RepositoryException;
import by.epam.booking.exception.ServiceException;
import by.epam.booking.repository.assistant.book.*;
import by.epam.booking.repository.assistant.plan.GetReadingPlanInfo;
import by.epam.booking.service.plan.ReadingPlanInfoType;
import by.epam.booking.service.plan.ReadingPlanLogicProtocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ReadingPlanLogic implements ReadingPlanLogicProtocol {

    private static ReadingPlanLogic INSTANCE;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static final ReentrantLock getInstanceLock = new ReentrantLock();
    private static Logger logger = LogManager.getLogger();

    private ReadingPlanLogic(){
    }

    public static ReadingPlanLogic getInstance() {
        if (!instanceCreated.get()) {
            getInstanceLock.lock();
            try {
                if (null == INSTANCE) {
                    INSTANCE = new ReadingPlanLogic();
                    instanceCreated.set(true);
                }
            } finally {
                getInstanceLock.unlock();
            }
        }
        return INSTANCE;
    }

    @Override
    public ReadingPlan planGet(ReadingPlan transferredPlan, ReadingPlanInfoType... types) throws ServiceException {

        for (ReadingPlanInfoType type : types) {
            switch (type) {
                case GET_ALL_READING_PLANS:{

                }break;
                case GET_ALL_BOOKS_OF_PLAN:{
                    try {
                        transferredPlan.setBooks(GetAllBooks.getAllBooksInPlan(transferredPlan.getIdReadingPlan()));
                    } catch (RepositoryException e) {
                        logger.error(e);
                        throw new ServiceException(e);
                    }
                }break;
                case READING_PLAN_NAME:{
                    try {
                        transferredPlan.setName(GetReadingPlanInfo.getName(transferredPlan.getIdReadingPlan()));
                    } catch (RepositoryException e) {
                        throw new ServiceException(e);
                    }
                }break;
            }
        }
        return transferredPlan;
    }

}
