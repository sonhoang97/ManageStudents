package Main;

import DAO.*;
import ModelEntity.Schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        excelDAO excelDAO = new excelDAO();
        ScheduleDAO schDAO = new ScheduleDAO();

        List<Schedule> schedules = new ArrayList<Schedule>();
        schedules = excelDAO.getSchedulesExcel();
        for (Schedule sch : schedules) {
            schDAO.addSchedule(sch);
        }
    }
}
