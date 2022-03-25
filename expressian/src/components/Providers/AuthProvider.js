import React, {useState} from 'react';

const AuthContext = React.createContext([]); // will accept an object and function as [ {} and () => {} ] this is optional, an empty array or an array is also fine.

const AuthProvider = (props) => {
  const [auth, setAuth] = useState({token: null, profile: null});

  //useEffect can check for local storage for previously logged in user.

  return (
    <AuthContext.Provider value={[auth, setAuth]}> //auth is an object and setAuth is a function that sets auth - - this is the hook that allows the data to be used by the children
      {props.children}
    </AuthContext.Provider>
  )
}

export {AuthProvider, AuthContext};