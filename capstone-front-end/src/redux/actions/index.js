export const GET_USER_LOGGED = "GET_USER_LOGGED";
export const LOGOUT_USER = "LOGOUT_USER";
export const GET_POSTS = "GET_POSTS";
export const GET_POST_TO_MODIFY = "GET_POST_TO_MODIFY";
export const GET_PRODUCTS = "GET_PRODUCTS";
export const GET_LIKES = "GET_LIKES";
export const GET_ALL_POSTS = "GET_ALL_POSTS";
export const GET_USER_LIBRARY = "GET_USER_LIBRARY";
export const GET_USER_POSTS = "GET_USER_POSTS";
export const CREATE_POST = "CREATE_POST";
export const CREATE_USER = "CREATE_USER";
export const GET_ALL_USERS = "GET_ALL_USERS";

export const getUserLoggedAction = () => {
  const token = localStorage.getItem("token");
  const url = "http://localhost:3142/users/me";
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_USER_LOGGED, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const logoutUserAction = () => {
  return { type: LOGOUT_USER };
};

export const getPostToModify = post => ({ type: GET_POST_TO_MODIFY, payload: post });

export const getMePosts = () => {
  const token = localStorage.getItem("token");
  const url = "http://localhost:3142/users/me/posts";
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_POSTS, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const getMeLibrary = () => {
  const token = localStorage.getItem("token");
  const url = "http://localhost:3142/users/me/library";
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_PRODUCTS, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const getMeLikes = () => {
  const token = localStorage.getItem("token");
  const url = "http://localhost:3142/users/me/likes";
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_LIKES, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const getAllPosts = url => {
  const token = localStorage.getItem("token");
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_POSTS, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const getAllUsers = () => {
  const token = localStorage.getItem("token");
  const url = "http://localhost:3142/users";
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_ALL_USERS, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const getUserLibrary = url => {
  const token = localStorage.getItem("token");
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_USER_LIBRARY, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const getUserPosts = url => {
  const token = localStorage.getItem("token");
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: GET_USER_POSTS, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};

export const createPost = url => {
  const token = localStorage.getItem("token");
  return async dispatch => {
    try {
      let resp = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      if (resp.ok) {
        let data = await resp.json();

        dispatch({ type: CREATE_POST, payload: data });
      }
    } catch (error) {
      console.log(error);
    }
  };
};
