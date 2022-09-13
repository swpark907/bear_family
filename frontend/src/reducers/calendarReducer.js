const CHANGE_DATE = "CALENDAR/CHANGE_DATE";
const CHANGE_MONTH = "CALENDAR/CHANGE_MONTH"

const initialState = {
  date: [],
  selectedMonthTotal: 0,
};

export const changeDate = ( data = [] ) => {
  return {
    type: CHANGE_DATE,
    payload: data,
  };
};

export const changeMonth = ( data = 0 ) => {
  return{
    type: CHANGE_MONTH,
    payload: data,
  }
}

export const calendarReducer = (state = initialState, action) => {
  switch (action.type) {
    case CHANGE_DATE:
      return {
        ...state,
        date: action.payload,
      };
      case CHANGE_MONTH:
        return{
          ...state,
          selectedMonthTotal: action.payload,
        }
    default:
      return state;
  }
};
