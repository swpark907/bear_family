const LOADING = "LOADING/LOADING";
const UNLOADING = "LOADING/UNLOADING";

const initialState = {
  isLoading: false,
};

export const loading = () => {
  return {
    type: LOADING,
  };
};

export const unloading = () => {
  return {
    type: UNLOADING,
  };
};

export const loadingReducer = (state = initialState, action) => {
  switch (action.type) {
    case LOADING:
      return {
        ...state,
        isLoading: true,
      };
    case UNLOADING:
      return {
        ...state,
        isLoading: false,
      };
    default:
      return state;
  }
};
