package by.epam.booking.service.plan;

import by.epam.booking.entity.ReadingPlan;
import by.epam.booking.exception.ServiceException;

/**
 * The interface Reading plan logic protocol.
 */
public interface ReadingPlanLogicProtocol {
    /**
     * Plan get reading plan.
     *
     * @param transferredPlan the transferred plan
     * @param types           the types
     * @return the reading plan
     * @throws ServiceException the service exception
     */
    ReadingPlan planGet(ReadingPlan transferredPlan, ReadingPlanInfoType... types) throws ServiceException;
}
