import {
  GET_USER_LOGGED,
  LOGOUT_USER,
  GET_POSTS,
  GET_POST_TO_MODIFY,
  GET_PRODUCTS,
  GET_LIKES,
  GET_ALL_POSTS,
  CREATE_POST,
  GET_ALL_USERS
} from "../actions";

const initialState = {
  user: null,
  users: [],
  posts: [],
  post: null,
  postEdit: null,
  library: [],
  product: null,
  productEdit: null,
  likes: [],
  allPosts: [],
  createdPost: {}
};

const homeReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_USER_LOGGED:
      return {
        ...state,
        user: action.payload
      };
    case LOGOUT_USER:
      return {
        ...state,
        user: null
      };
    case GET_POSTS:
      return {
        ...state,
        posts: action.payload
      };
    case GET_POST_TO_MODIFY:
      return {
        ...state,
        postEdit: action.payload
      };
    case GET_PRODUCTS:
      return {
        ...state,
        library: action.payload
      };
    case GET_LIKES:
      return {
        ...state,
        likes: action.payload
      };
    case GET_ALL_POSTS:
      return {
        ...state,
        allPosts: action.payload
      };
    case CREATE_POST:
      return {
        ...state,
        createdPost: action.payload
      };
    case GET_ALL_USERS:
      return {
        ...state,
        users: action.payload
      };
    default:
      return state;
  }
};

export default homeReducer;
