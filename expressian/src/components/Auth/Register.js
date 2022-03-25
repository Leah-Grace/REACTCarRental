import React, {useState} from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import NewUserForm from './NewUserForm';
import Container from '../common/Container';
import Splash from '../common/Splash';
import splashImg from '../../assets/SplashParallel.jpg';
import { apiHostUrl } from '../../config'
//import RegSplash from "../../assets/RegSplash.jpg";

const Register = (props) => {
    const [newUser, setNewUser] = useState({
        email: '',
        password: '',
        fName: '',
        lName: '',
        isMember: false,
        licenseNumber: ''
    })

    const navigate = useNavigate();
    const updateForm = (field, value) => {
        setNewUser({
            ...newUser,
            [field]: value
        })
    }

    const onSubmit = (e) => {
        alert("Submitted");
        const data = newUser;
        data.name = `${data.fName} ${data.lName}`;
        data.username = data.email;

        // post create user, login, create customer,
        createUser(data);
    };

const createUser = async (data) => {
    try{
        const res = await axios.post(`${apiHostUrl}/api/auth/signup`, data);
        console.log(res.data);
        login(data);
    } catch (err) {
        console.error(err.message);
        }
    }

const login = async (data) => {
    try{
        const res = await axios.post(`${apiHostUrl}/api/auth/signin`, data);
        //put format is not as popular anymore. Post is for creating, put is for updating one or two fields.
        console.log(res.data);

    } catch (err) {
    console.error(err.response.data);
    }
}

const createCustomer = async (data, token) => {
    try {
        const res = await axios.post(
            `${apiHostUrl}/customers`,
            data,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        );
        console.log(res.data);
        navigate('/login');
    } catch (err){
        console.error(err.response.data);
    }


}




    return (
    <Container>
    <Splash image={splashImg} style={{
    height: '20vh',
    color: '#F1F1F1'
    }}>
        <h1>Register</h1>
        </ Splash>
        <NewUserForm
        newUser={newUser}
        onChange={updateForm}
        onSubmit={onSubmit}
        />
        </Container>
    )
}

export default Register;


       // <NewUserForm
           // newUser={newUser}
           // onChange={updateForm}
           // onSubmit={onSubmit}
      //  />