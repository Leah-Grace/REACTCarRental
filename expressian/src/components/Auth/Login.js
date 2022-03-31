import React, { useState, useContext } from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import LoginForm from './LoginForm';
import Container from '../common/Container';
import Splash from '../common/Splash';
import splashImg from '../../assets/SplashParallel.jpg';
import { apiHostUrl } from '../../config';
import {AuthContext} from '../Providers/AuthProvider'



const Login = (props) => {

const [newLogin, setNewLogin] = useState({
    email: '',
    password: ''
});

const [auth, setAuth] = useContext(AuthContext);
const navigate = useNavigate();


const updateForm = (field, value) => {
    setNewLogin({
        ...newLogin,
        [field]: value
    })
    }

const onSubmit = (e) => {
    alert("Submit clicked");
    const data = newLogin;
    login(data);
}

const login = async (data) => {
    try {
        const res = await axios.post(`${apiHostUrl}/api/auth/signin`, data);
        console.log(res.data);
        setAuth({
            token: res.data.token,
            profile: {},
            roles: res.data.roles
        })
    navigate("/");
    } catch (err) {
        console.log(err.response ? err.response.data : err.message)
    }
}

    return (
       <Container>
       <Splash image={splashImg} style={{
            height: '30vh',
            color: '#F1F1F1'
            }}>
        <h1>Login</h1>
        </Splash>
        <LoginForm
            newLogin={newLogin}
            onChange={updateForm}
            onSubmit={onSubmit}
            />
        </Container>

    )
}

export default Login;