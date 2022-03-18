import React, {useState} from 'react';
import NewUserForm from './NewUserForm';
import Container from '../common/Container';
import LoginForm from './LoginForm';
import Splash from '../common/Splash';
import splashImg from '../../assets/SplashParallel.jpg';
import axios from 'axios';
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

    const updateForm = (field, value) => {
        setNewUser({
            ...newUser,
            [field]: value
        })
    }

    const onSubmit = () => {
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
    } catch (err) {
        console.error(err.message);
        }
    }

const login = (data) => {

}

const createCustomer = (data, token) => {

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