import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Registration = () => {
  const [registration, setRegistration] = useState({
    username: "",
    name: "",
    lastname: "",
    email: "",
    password: "",
    aboutMe: "",
    profileImage: ""
  });
  const navigate = useNavigate();
  const sendRegistration = async e => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:3142/auth/registration`, {
        method: "POST",
        body: JSON.stringify(registration),
        headers: {
          Authorization: `Bearer ${process.env.TOKEN}`
        }
      });
      if (response.ok) {
        setRegistration({
          username: "",
          name: "",
          lastname: "",
          email: "",
          password: "",
          aboutMe: "",
          profileImage: ""
        });
        navigate("/");
      }
    } catch (error) {
      alert(error);
    }
  };

  return (
    <Container className="text-dark d-flex justify-content-center align-items-center">
      <Form className="rounded p-5 mt-5 form-register" onSubmit={sendRegistration}>
        <Form.Group className="mb-3">
          <h2 className="text-white mb-5">form registrazione</h2>
          <Form.Control
            required
            type="text"
            className="bg-white text-dark input-login"
            placeholder="Username"
            value={registration.username}
            onChange={e => setRegistration({ ...registration, username: e.target.value })}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Control
            required
            type="text"
            className="bg-white text-dark input-login"
            placeholder="Nome"
            value={registration.name}
            onChange={e => setRegistration({ ...registration, name: e.target.value })}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Control
            required
            type="text"
            className="bg-white text-dark input-login"
            placeholder="Cognome"
            value={registration.lastname}
            onChange={e => setRegistration({ ...registration, lastname: e.target.value })}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Control
            required
            type="email"
            className="bg-white text-dark input-login"
            placeholder="Email"
            value={registration.email}
            onChange={e => setRegistration({ ...registration, email: e.target.value })}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <div className="input-with-icon">
            <Form.Control
              required
              type={"password"}
              className="bg-white text-dark input-login"
              placeholder="Password"
              value={registration.password}
              onChange={e => setRegistration({ ...registration, password: e.target.value })}
            />
          </div>
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Control
            required
            type="text"
            className="bg-white text-dark input-login"
            placeholder="Parla in breve di te"
            value={registration.aboutMe}
            onChange={e => setRegistration({ ...registration, aboutMe: e.target.value })}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Control
            required
            type="text"
            className="bg-white text-dark input-login"
            placeholder="Linka un'immagine profilo"
            value={registration.profileImage}
            onChange={e => setRegistration({ ...registration, profileImage: e.target.value })}
          />
        </Form.Group>
        <div className="d-flex justify-content-center">
          <Button variant="dark" className="border-0">
            Registrati
          </Button>
        </div>
      </Form>
    </Container>
  );
};

export default Registration;
