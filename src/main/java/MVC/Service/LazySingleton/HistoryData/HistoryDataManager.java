package MVC.Service.LazySingleton.HistoryData;

import MVC.Service.Enum.HistoryDataState;

public class HistoryDataManager {
    private static HistoryDataManager instance;
    private HistoryDataState historyDataState;

    private HistoryDataManager() {
        // Đặt trạng thái mặc định
        this.historyDataState = HistoryDataState.NO_LOADING;
    }

    public static HistoryDataManager getInstance() {
        if (instance == null) {
            instance = new HistoryDataManager();
        }
        return instance;
    }

    public HistoryDataState getHistoryDataState() {
        return historyDataState;
    }

    public void setHistoryDataState(HistoryDataState historyDataState) {
        this.historyDataState = historyDataState;
    }
}
