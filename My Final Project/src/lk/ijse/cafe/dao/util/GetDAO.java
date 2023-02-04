package lk.ijse.cafe.dao.util;

import lk.ijse.cafe.entity.SupperEntity;

import java.io.Serializable;

public interface GetDAO <T extends SupperEntity,ID extends Serializable>extends SuperDAO{
    public String getNextId();
    public String generateNextId();
}
