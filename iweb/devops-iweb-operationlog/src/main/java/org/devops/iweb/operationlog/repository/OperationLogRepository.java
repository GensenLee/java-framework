package org.devops.iweb.operationlog.repository;


import lombok.Setter;
import org.devops.core.model.core.AbstractModelRepository;
import org.devops.core.model.interfaces.IModel;
import org.springframework.stereotype.Repository;
import org.devops.iweb.operationlog.model.OperationLog;

import java.util.List;

@Repository
public class OperationLogRepository extends AbstractModelRepository<OperationLog, Long>{

    @Override
    public IModel<OperationLog, Long> getModel() {
        return super.getModel();
    }

    @Override
    public List<OperationLog> list() {
        this.tableSuffix(SubTableControl.getTableSuffix());
        return super.list();
    }

    @Override
    public OperationLog get() {
        this.tableSuffix(SubTableControl.getTableSuffix());
        return super.get();
    }

    @Override
    public long count() {
        this.tableSuffix(SubTableControl.getTableSuffix());
        return super.count();
    }

    @Override
    public long insert(OperationLog insertModel) {
        this.tableSuffix(SubTableControl.getTableSuffix());
        return super.insert(insertModel);
    }

}
