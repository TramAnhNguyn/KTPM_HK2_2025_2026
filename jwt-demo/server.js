const express = require("express");
const jwt = require("jsonwebtoken");
const cors = require("cors");

const app = express();
app.use(express.json());
app.use(cors());

const PORT = 3000;

// SECRET KEY
const ACCESS_TOKEN_SECRET = "access_secret_key";
const REFRESH_TOKEN_SECRET = "refresh_secret_key";

// Fake database
const users = [
  { id: 1, username: "admin", password: "123", role: "admin" },
  { id: 2, username: "guest", password: "123", role: "guest" }
];

let refreshTokens = [];

// 🔐 LOGIN
app.post("/login", (req, res) => {
  const { username, password } = req.body;

  const user = users.find(
    u => u.username === username && u.password === password
  );

  if (!user) {
    return res.status(401).json({ message: "Invalid credentials" });
  }

  const payload = { id: user.id, role: user.role };

  const accessToken = jwt.sign(payload, ACCESS_TOKEN_SECRET, {
    expiresIn: "1m"
  });

  const refreshToken = jwt.sign(payload, REFRESH_TOKEN_SECRET, {
    expiresIn: "7d"
  });

  refreshTokens.push(refreshToken);

  res.json({
    accessToken,
    refreshToken
  });
});

// 🔁 REFRESH TOKEN
app.post("/refresh", (req, res) => {
  const { refreshToken } = req.body;

  if (!refreshToken || !refreshTokens.includes(refreshToken)) {
    return res.sendStatus(403);
  }

  jwt.verify(refreshToken, REFRESH_TOKEN_SECRET, (err, user) => {
    if (err) return res.sendStatus(403);

    const newAccessToken = jwt.sign(
      { id: user.id, role: user.role },
      ACCESS_TOKEN_SECRET,
      { expiresIn: "1m" }
    );

    res.json({ accessToken: newAccessToken });
  });
});

// 👑 ADMIN API
const verifyToken = require("./authMiddleware");

app.get("/admin", verifyToken("admin"), (req, res) => {
  res.json({ message: "Welcome Admin 👑" });
});

// 👤 GUEST API
app.get("/guest", verifyToken("guest"), (req, res) => {
  res.json({ message: "Welcome Guest 👤" });
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
