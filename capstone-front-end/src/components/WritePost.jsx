import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { createPost } from "../redux/actions";
import HomeNavbar from "./HomeNavbar";

const WritePost = () => {
  const [post, setPost] = useState({
    title: "",
    content: "",
    postImage: "",
    category: ""
  });

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const sendPost = async e => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:3142/posts`, {
        method: "POST",
        body: JSON.stringify(post),
        headers: {
          "Content-Type": "application/json"
        }
      });
      if (response.ok) {
        setPost({
          title: "",
          content: "",
          postImage: "",
          category: ""
        });
        dispatch(createPost());
        navigate("/loggedHome");
      }
    } catch (error) {
      alert(error);
    }
  };

  return (
    <>
      <HomeNavbar />
      <Container className="text-dark d-flex justify-content-center align-items-center">
        <Form className="rounded p-5 mt-5 form-register" onSubmit={sendPost}>
          <Form.Group className="mb-3">
            <h2 className="text-white mb-5">Srivi un post</h2>
            <Form.Control
              required
              type="text"
              className="bg-white text-dark input-login"
              placeholder="Titolo post"
              value={post.title}
              onChange={e => setPost({ ...post, title: e.target.value })}
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Control
              required
              type="text"
              className="bg-white text-dark input-login"
              placeholder="Contenuto del tuo post"
              value={post.content}
              onChange={e => setPost({ ...post, content: e.target.value })}
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Control
              required
              type="text"
              className="bg-white text-dark input-login"
              placeholder="Immagine per il post"
              value={post.postImage}
              onChange={e => setPost({ ...post, postImage: e.target.value })}
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Control
              required
              type="email"
              className="bg-white text-dark input-login"
              placeholder="Scrivi una categoria"
              value={post.category}
              onChange={e => setPost({ ...post, category: e.target.value })}
            />
          </Form.Group>
          <div className="d-flex justify-content-center">
            <Button variant="dark" className="border-0">
              Posta
            </Button>
          </div>
        </Form>
      </Container>
    </>
  );
};

export default WritePost;
