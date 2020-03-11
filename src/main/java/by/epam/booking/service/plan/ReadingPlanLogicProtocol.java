package by.epam.booking.service.plan;

import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.exception.ServiceException;

public interface ReadingPlanLogicProtocol {
    ReadingPlan planGet(ReadingPlan transferredPlan, ReadingPlanInfoType... types) throws ServiceException;
}
