import { useState } from "react";
import { Alert, Button, Container, Form, InputGroup } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getUserLoggedAction } from "../redux/actions";

const Login = () => {
  const [login, setLogin] = useState({
    email: "",
    password: "",
    showPassword: false
  });

  const [error, setError] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const sendLogin = async e => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:3142/auth/login`, {
        method: "POST",
        body: JSON.stringify(login),
        headers: {
          "Content-Type": "application/json"
        }
      });
      if (response.ok) {
        const data = await response.json();
        localStorage.setItem("token", data.accessToken);
        setLogin({
          email: "",
          password: "",
          showPassword: false
        });
        dispatch(getUserLoggedAction());
        navigate("/loggedHome");
      }
    } catch (error) {
      setErrorMessage(error);
      setErrorMessage("Credenziali non valide!");
    }
  };

  return (
    <Container className="text-dark d-flex justify-content-center align-items-center">
      {error && (
        <Alert variant="danger" onClose={() => setError(null)} dismissible>
          {errorMessage}
        </Alert>
      )}
      <Form className="rounded p-5 mt-5 form-login" onSubmit={sendLogin}>
        <Form.Group className="mb-3 text-dark">
          <Form.Control
            required
            type="text"
            className="bg-white text-dark input-login"
            placeholder="email"
            value={login.email}
            onChange={e => setLogin({ ...login, email: e.target.value })}
          />
        </Form.Group>
        <Form.Group className="mb-3 text-dark">
          <InputGroup>
            <Form.Control
              required
              type={login.showPassword ? "text" : "password"}
              className="bg-white text-dark input-login"
              placeholder="password"
              value={login.password}
              onChange={e => setLogin({ ...login, password: e.target.value })}
            />
          </InputGroup>
        </Form.Group>
        <div className="d-flex justify-content-center">
          <Button className="border-0" variant="primary" type="submit">
            Sign in
          </Button>
        </div>
      </Form>
    </Container>
  );
};

export default Login;
